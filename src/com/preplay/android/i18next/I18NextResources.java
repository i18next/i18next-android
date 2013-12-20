package com.preplay.android.i18next;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParserException;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;

public class I18NextResources extends Resources {
    /** Used locally to tag Logs */
    private static final String TAG = I18NextResources.class.getSimpleName();

    private Resources mBaseResources;

    public I18NextResources(Resources res) {
        super(res.getAssets(), res.getDisplayMetrics(), res.getConfiguration());
        mBaseResources = res;
    }

    public CharSequence getTextTranslate(CharSequence txt) {
        return getTextTranslate(txt, null);
    }

    public CharSequence getTextTranslate(CharSequence txt, Operation operation) {
        if (I18Next.isI18NextKeyCandidate(txt)) {
            String res = I18Next.getInstance().t(txt.toString(), operation);
            if (res == null) {
                Log.w(TAG, "Key '" + txt + "' not found into I18Next");
            } else {
                return res;
            }
        }
        return txt;
    }

    public String getTextTranslate(String txt) {
        if (I18Next.isI18NextKeyCandidate(txt)) {
            return I18Next.getInstance().t(txt);
        } else {
            return txt;
        }
    }

    @Override
    public CharSequence getText(int id) throws NotFoundException {
        return getText(id, null);
    }

    @Override
    public CharSequence getText(int id, CharSequence def) {
        CharSequence res;
        try {
            res = mBaseResources.getText(id);
        } catch (NotFoundException ex) {
            res = def;
        }
        return getTextTranslate(res);
    }

    @Override
    public CharSequence getQuantityText(int id, int quantity) throws NotFoundException {
        return getTextTranslate(mBaseResources.getText(id), new Operation.Plural(quantity));
    }

    @Override
    public CharSequence[] getTextArray(int id) throws NotFoundException {
        return mBaseResources.getTextArray(id);
    }

    @Override
    public String[] getStringArray(int id) throws NotFoundException {
        return mBaseResources.getStringArray(id);
    }

    @Override
    public int[] getIntArray(int id) throws NotFoundException {
        return mBaseResources.getIntArray(id);
    }

    @SuppressLint("Recycle")
    @Override
    public TypedArray obtainTypedArray(int id) throws NotFoundException {
        return mBaseResources.obtainTypedArray(id);
    }

    @Override
    public float getDimension(int id) throws NotFoundException {
        return mBaseResources.getDimension(id);
    }

    @Override
    public int getDimensionPixelOffset(int id) throws NotFoundException {
        return mBaseResources.getDimensionPixelOffset(id);
    }

    @Override
    public int getDimensionPixelSize(int id) throws NotFoundException {
        return mBaseResources.getDimensionPixelSize(id);
    }

    @Override
    public float getFraction(int id, int base, int pbase) {
        return mBaseResources.getFraction(id, base, pbase);
    }

    @Override
    public Drawable getDrawable(int id) throws NotFoundException {
        return mBaseResources.getDrawable(id);
    }

    @Override
    public Drawable getDrawableForDensity(int id, int density) throws NotFoundException {
        return mBaseResources.getDrawableForDensity(id, density);
    }

    @Override
    public Movie getMovie(int id) throws NotFoundException {
        return mBaseResources.getMovie(id);
    }

    @Override
    public int getColor(int id) throws NotFoundException {
        return mBaseResources.getColor(id);
    }

    @Override
    public ColorStateList getColorStateList(int id) throws NotFoundException {
        return mBaseResources.getColorStateList(id);
    }

    @Override
    public boolean getBoolean(int id) throws NotFoundException {
        return mBaseResources.getBoolean(id);
    }

    @Override
    public int getInteger(int id) throws NotFoundException {
        return mBaseResources.getInteger(id);
    }

    @Override
    public XmlResourceParser getLayout(int id) throws NotFoundException {
        return mBaseResources.getLayout(id);
    }

    @Override
    public XmlResourceParser getAnimation(int id) throws NotFoundException {
        return mBaseResources.getAnimation(id);
    }

    @Override
    public XmlResourceParser getXml(int id) throws NotFoundException {
        return mBaseResources.getXml(id);
    }

    @Override
    public InputStream openRawResource(int id) throws NotFoundException {
        return mBaseResources.openRawResource(id);
    }

    @Override
    public InputStream openRawResource(int id, TypedValue value) throws NotFoundException {
        return mBaseResources.openRawResource(id, value);
    }

    @Override
    public AssetFileDescriptor openRawResourceFd(int id) throws NotFoundException {
        return mBaseResources.openRawResourceFd(id);
    }

    @Override
    public void getValue(int id, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        mBaseResources.getValue(id, outValue, resolveRefs);
    }

    @Override
    public void getValueForDensity(int id, int density, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        mBaseResources.getValueForDensity(id, density, outValue, resolveRefs);
    }

    @Override
    public void getValue(String name, TypedValue outValue, boolean resolveRefs) throws NotFoundException {
        mBaseResources.getValue(name, outValue, resolveRefs);
    }

    @SuppressLint("Recycle")
    @Override
    public TypedArray obtainAttributes(AttributeSet set, int[] attrs) {
        return mBaseResources.obtainAttributes(set, attrs);
    }

    @Override
    public void updateConfiguration(Configuration config, DisplayMetrics metrics) {
        if (mBaseResources != null) {
            mBaseResources.updateConfiguration(config, metrics);
        }
    }

    @Override
    public DisplayMetrics getDisplayMetrics() {
        return mBaseResources.getDisplayMetrics();
    }

    @Override
    public Configuration getConfiguration() {
        return mBaseResources.getConfiguration();
    }

    @Override
    public int getIdentifier(String name, String defType, String defPackage) {
        return mBaseResources.getIdentifier(name, defType, defPackage);
    }

    @Override
    public String getResourceName(int resid) throws NotFoundException {
        return mBaseResources.getResourceName(resid);
    }

    @Override
    public String getResourcePackageName(int resid) throws NotFoundException {
        return mBaseResources.getResourcePackageName(resid);
    }

    @Override
    public String getResourceTypeName(int resid) throws NotFoundException {
        return mBaseResources.getResourceTypeName(resid);
    }

    @Override
    public String getResourceEntryName(int resid) throws NotFoundException {
        return mBaseResources.getResourceEntryName(resid);
    }

    @Override
    public void parseBundleExtras(XmlResourceParser parser, Bundle outBundle) throws XmlPullParserException, IOException {
        mBaseResources.parseBundleExtras(parser, outBundle);
    }

    @Override
    public void parseBundleExtra(String tagName, AttributeSet attrs, Bundle outBundle) throws XmlPullParserException {
        mBaseResources.parseBundleExtra(tagName, attrs, outBundle);
    }

}
