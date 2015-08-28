/**
 * 
 */
package com.preplay.android.i18next;

import java.util.Locale;

/**
 * @author stan
 * 
 */
public class Options {
    /** Used locally to tag Logs */
    @SuppressWarnings("unused")
    private static final String TAG = Options.class.getSimpleName();

    private boolean mDebugMode = BuildConfig.DEBUG;
    private String[] mNameSpaces;
    private String mDefaultNameSpace;
    private String mReusePrefix = "$t(";
    private String mReuseSuffix = ")";
    private String mContextPrefix = "_";
    private String mInterpolationPrefix = "__";
    private String mInterpolationSuffix = "__";
    private String mPluralSuffix = "_plural";
    private String mNsSeparator = ":";
    private String mKeySeparator = ".";
    private String mForcedLanguage;
    private String mFallbackLanguage;

    public Options setNamespaces(String... namespaces) {
        mNameSpaces = namespaces;
        return this;
    }

    public Options setDefaultNamespace(String namespace) {
        mDefaultNameSpace = namespace;
        return this;
    }

    public String getReusePrefix() {
        return mReusePrefix;
    }

    public Options setReusePrefix(String reusePrefix) {
        mReusePrefix = reusePrefix;
        return this;
    }

    public String getReuseSuffix() {
        return mReuseSuffix;
    }

    public Options setReuseSuffix(String reuseSuffix) {
        mReuseSuffix = reuseSuffix;
        return this;
    }

    public String[] getNamespaces() {
        return mNameSpaces;
    }

    public String getDefaultNamespace() {
        return mDefaultNameSpace;
    }

    public String getInterpolationPrefix() {
        return mInterpolationPrefix;
    }

    public Options setInterpolationPrefix(String interpolationPrefix) {
        mInterpolationPrefix = interpolationPrefix;
        return this;
    }

    public String getInterpolationSuffix() {
        return mInterpolationSuffix;
    }

    public Options setInterpolationSuffix(String interpolationSuffix) {
        mInterpolationSuffix = interpolationSuffix;
        return this;
    }

    public boolean isDebugMode() {
        return mDebugMode;
    }

    public Options setDebugMode(boolean debugMode) {
        mDebugMode = debugMode;
        return this;
    }

    public String getPluralSuffix() {
        return mPluralSuffix;
    }

    public Options setPluralSuffix(String pluralSuffix) {
        mPluralSuffix = pluralSuffix;
        return this;
    }

    public String getNsSeparator() {
        return mNsSeparator;
    }

    public Options setNsSeparator(String nsSeparator) {
        mNsSeparator = nsSeparator;
        return this;
    }

    public String getKeySeparator() {
        return mKeySeparator;
    }

    public Options setKeySeparator(String keySeparator) {
        mKeySeparator = keySeparator;
        return this;
    }

    public String getContextPrefix() {
        return mContextPrefix;
    }

    public Options setContextPrefix(String contextPrefix) {
        mContextPrefix = contextPrefix;
        return this;
    }

    public String getLanguage() {
        if (mForcedLanguage != null) {
            return mForcedLanguage;
        } else {
            return Locale.getDefault().toString();
        }
    }

    public Options setLanguage(String forcedLanguage) {
        mForcedLanguage = I18Next.getConvertLang(forcedLanguage);
        return this;
    }

    public Options setLanguageAutoDetected() {
        return setLanguage(null);
    }

    public String getFallbackLanguage() {
        return mFallbackLanguage;
    }

    public Options setFallbackLanguage(String fallbackLanguage) {
        mFallbackLanguage = I18Next.getConvertLang(fallbackLanguage);
        return this;
    }

}
