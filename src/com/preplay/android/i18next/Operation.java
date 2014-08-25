/**
 *
 */
package com.preplay.android.i18next;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author stan
 */
public interface Operation {

    public interface PostOperation extends Operation {
        public abstract String postProcess(String source);
    }

    public interface PreOperation extends Operation {
        public abstract String preProcess(String key);

        public abstract String preProcessAfterNoValueFound(String key);
    }

    public static class MultiPostProcessing implements PostOperation, PreOperation {
        private Operation[] mOperations;

        public MultiPostProcessing(Operation... operations) {
            mOperations = operations;
        }

        @Override
        public String postProcess(String source) {
            if (mOperations != null) {
                for (Operation operation : mOperations) {
                    if (operation instanceof PostOperation) {
                        source = ((PostOperation) operation).postProcess(source);
                    }
                }
            }
            return source;
        }

        @Override
        public String preProcess(String key) {
            if (mOperations != null) {
                for (Operation operation : mOperations) {
                    if (operation instanceof PreOperation) {
                        key = ((PreOperation) operation).preProcess(key);
                    }
                }
            }
            return key;
        }

        @Override
        public String preProcessAfterNoValueFound(String key) {
            if (mOperations != null) {
                for (Operation operation : mOperations) {
                    if (operation instanceof PreOperation) {
                        String keyTemp = ((PreOperation) operation).preProcessAfterNoValueFound(key);
                        if (keyTemp != null && !keyTemp.equals(key)) {
                            return keyTemp;
                        }
                    }
                }
            }
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof MultiPostProcessing) {
                Operation[] otherOperations = ((MultiPostProcessing) o).mOperations;
                int nbElement = (mOperations == null) ? 0 : mOperations.length;
                int nbElementOther = (otherOperations == null) ? 0 : otherOperations.length;
                if (nbElement == nbElementOther) {
                    if (nbElement == 0) {
                        return true;
                    } else {
                        for (int i = 0; i < nbElement; i++) {
                            Operation operation1 = mOperations[i];
                            Operation operation2 = otherOperations[i];
                            if ((operation1 != null && !operation1.equals(operation2)) || (operation1 == null && operation2 == null)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class Plural implements PreOperation, PostOperation {
        private int mCount;
        private Interpolation mInterpolation;

        public Plural(int count) {
            this("count", count);
        }

        public Plural(String key, int count) {
            mInterpolation = new Interpolation(key, Integer.toString(count));
            mCount = count;
        }

        @Override
        public String preProcess(String key) {
            if (key != null) {
                int pluralExtension = Plurals.getInstance().get(I18Next.getInstance().getOptions().getLanguage(), mCount);

                if (pluralExtension != 1) {
                    // plural
                    key = key + I18Next.getInstance().getOptions().getPluralSuffix();
                    if (pluralExtension >= 0) {
                        // specific plural
                        key = key + "_" + pluralExtension;
                    }
                }
            }
            return key;
        }

        @Override
        public String preProcessAfterNoValueFound(String key) {
            int index = key.lastIndexOf(I18Next.getInstance().getOptions().getPluralSuffix());
            if (index > 0) {
                return key.substring(0, index);
            } else {
                return null;
            }
        }

        @Override
        public String postProcess(String source) {
            return mInterpolation.postProcess(source);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Plural) {
                Plural oPlural = (Plural) o;
                return oPlural.mCount == mCount && mInterpolation.equals(oPlural.mInterpolation);
            }
            return false;
        }
    }

    public static class Context implements PreOperation {
        private String mContextPrefix;

        public Context(String value) {
            mContextPrefix = I18Next.getInstance().getOptions().getContextPrefix() + value;
        }

        @Override
        public String preProcess(String key) {
            if (mContextPrefix != null && mContextPrefix.length() > 0 && key != null) {
                String keyWithContext = key + mContextPrefix;
                if (I18Next.getInstance().existValue(keyWithContext)) {
                    key = keyWithContext;
                }
            }
            return key;
        }

        @Override
        public String preProcessAfterNoValueFound(String key) {
            return null;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Context) {
                return I18Next.equalsCharSequence(mContextPrefix, ((Context) o).mContextPrefix);
            }
            return false;
        }
    }

    public static class SPrintF implements PostOperation {
        private Object[] mArgs;

        public SPrintF(Object... args) {
            mArgs = args;
        }

        @Override
        public String postProcess(String source) {
            if (source != null && mArgs != null && mArgs.length > 0) {
                source = String.format(source, mArgs);
            }
            return source;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof SPrintF) {
                Object[] otherArgs = ((SPrintF) o).mArgs;
                int nbElement = (mArgs == null) ? 0 : mArgs.length;
                int nbElementOther = (otherArgs == null) ? 0 : otherArgs.length;
                if (nbElement == nbElementOther) {
                    if (nbElement == 0) {
                        return true;
                    } else {
                        for (int i = 0; i < nbElement; i++) {
                            Object arg1 = mArgs[i];
                            Object arg2 = otherArgs[i];
                            if ((arg1 != null && !arg1.equals(arg2)) || (arg1 == null && arg2 == null)) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class Interpolation implements PostOperation {
        private StringBuffer mStringBuffer = new StringBuffer();
        private Map<String, Object> mReplacementMap;

        private static Map<String, Object> getReplacementMap(CharSequence target, CharSequence replacement) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(target.toString(), replacement);
            return map;
        }

        public Interpolation(CharSequence target, CharSequence replacement) {
            this(getReplacementMap(target, replacement));
        }

        public Interpolation(Map<String, Object> replacementMap) {
            mReplacementMap = replacementMap;
        }

        @Override
        public String postProcess(String source) {
            if (source != null) {
                Options options = I18Next.getInstance().getOptions();
                String interpolationPrefix = options.getInterpolationPrefix();
                int interpolationPrefixLength = interpolationPrefix.length();
                String interpolationSuffix = options.getInterpolationSuffix();

                int lastIndexOfInterpolationPrefix = -1;
                while (true) {
                    int indexOfInterpolationPrefix = source.indexOf(interpolationPrefix);
                    if (indexOfInterpolationPrefix < 0 || lastIndexOfInterpolationPrefix == indexOfInterpolationPrefix) {
                        break;
                    } else {
                        int indexOfInterpolationSuffix = source.indexOf(interpolationSuffix, indexOfInterpolationPrefix + interpolationPrefixLength);
                        if (indexOfInterpolationSuffix < 0) {
                            break;
                        }
                        String target = source.substring(indexOfInterpolationPrefix + interpolationPrefixLength, indexOfInterpolationSuffix);
                        Object replacement = getObject(target);
                        if(replacement != null) {
                            mStringBuffer.setLength(0);
                            mStringBuffer.append(interpolationPrefix);
                            mStringBuffer.append(target);
                            mStringBuffer.append(interpolationSuffix);
                            source = source.replace(mStringBuffer.toString(), replacement == null ? "" : replacement.toString());
                        }
                        lastIndexOfInterpolationPrefix = indexOfInterpolationPrefix;
                    }
                }
            }
            return source;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Interpolation) {
                return mReplacementMap != null && mReplacementMap.equals(((Interpolation) o).mReplacementMap);
            }
            return false;
        }

        private Object getObject(String target) {
            if (target != null) {
                String[] targetParts = target.split("\\.");
                if (targetParts != null && targetParts.length > 0) {
                    Object rootObject = mReplacementMap;
                    for (int i = 0; i < targetParts.length; i++) {
                        String part = targetParts[i];
                        if (rootObject instanceof Map<?, ?>) {
                            rootObject = ((Map<?, ?>) rootObject).get(part);
                        } else {
                            I18Next.getInstance().log(I18Next.LogMode.WARNING, "Impossible to replace '%s': not found", target);
                            return null;
                        }
                    }
                    return rootObject;
                }
            }
            return null;
        }
    }

    public static class DefaultValue implements PostOperation {
        private String mValue;

        public DefaultValue(String value) {
            mValue = value;
        }

        @Override
        public String postProcess(String source) {
            if (source == null) {
                source = mValue;
            }
            return source;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof DefaultValue) {
                return I18Next.equalsCharSequence(mValue, ((DefaultValue) o).mValue);
            }
            return false;
        }
    }
}
