/**
 * 
 */
package com.preplay.android.i18next;

/**
 * @author stan
 * 
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
    }

    public static class Interpolation implements PostOperation {
        private StringBuffer mStringBuffer = new StringBuffer();
        private CharSequence mTarget;
        private CharSequence mReplacement;

        public Interpolation(CharSequence target, CharSequence replacement) {
            mTarget = target;
            mReplacement = replacement;
        }

        @Override
        public String postProcess(String source) {
            if (source != null && mTarget != null && mTarget.length() > 0) {
                Options options = I18Next.getInstance().getOptions();
                String interpolationPrefix = options.getInterpolationPrefix();
                String interpolationSuffix = options.getInterpolationSuffix();
                mStringBuffer.setLength(0);
                mStringBuffer.append(interpolationPrefix);
                mStringBuffer.append(mTarget);
                mStringBuffer.append(interpolationSuffix);
                source = source.replace(mStringBuffer.toString(), mReplacement);
            }
            return source;
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
    }
}
