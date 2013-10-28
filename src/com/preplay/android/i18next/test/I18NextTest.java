/**
 * 
 */
package com.preplay.android.i18next.test;

import junit.framework.TestCase;

import org.json.JSONException;

import android.util.Log;

import com.preplay.android.i18next.I18Next;
import com.preplay.android.i18next.Operation;

/**
 * @author stan
 * 
 */
public class I18NextTest extends TestCase {
    /** Used locally to tag Logs */
    private static final String TAG = I18NextTest.class.getSimpleName();

    @Override
    public void setUp() {
        try {
            super.setUp();
        } catch (Exception e) {
            Log.w("TAG", e);
        }
        try {
            // {
            // "app" : {
            // "name" : "i18next",
            // "insert" : "you are __youAre__",
            // "sprintf" : "%s, %s, %s and %s",
            // "sprintf2" : "%d %f",
            // "child" : "__count__ child",
            // "child_plural" : "__count__ children",
            // "child2" : "a child",
            // "child2_plural" : "some children",
            // "area" : "Area 51",
            // "district" : "District 9 is more fun than $t(app.area)",
            // "friend_context" : "A friend",
            // "friend_context_male" : "A boyfriend",
            // "friend_context_female" : "A girlfriend"
            // }
            // }
            String commonEn = "{\"app\":{\"name\":\"i18next\",\"insert\":\"you are __youAre__\",\"sprintf\":\"%s, %s, %s and %s\",\"sprintf2\":\"%d %f\",\"child\":\"__count__ child\",\"child_plural\":\"__count__ children\",\"child2\":\"a child\",\"child2_plural\":\"some children\",\"area\":\"Area 51\",\"district\":\"District 9 is more fun than $t(app.area)\",\"friend_context\":\"A friend\",\"friend_context_male\":\"A boyfriend\",\"friend_context_female\":\"A girlfriend\"}}";
            I18Next.getInstance().load("en", "common", commonEn);
        } catch (JSONException e) {
            Log.w(TAG, e);
            fail(e.getMessage());
        }
        try {
            // {
            // "app" : {
            // "name" : "i18next"
            // }
            // }
            String commonEn = "{\"app\":{\"name\":\"i18nextspecific\"}}";
            I18Next.getInstance().load("en", "specific", commonEn);
        } catch (JSONException e) {
            Log.w(TAG, e);
            fail(e.getMessage());
        }
        I18Next.getInstance().getOptions().setDefaultNamespace("common");
    }

    public void testShouldReturnValueDefault() {
        assertEquals(I18Next.getInstance().t("notexist"), null);
        assertEquals(I18Next.getInstance().t("notexist", new Operation.DefaultValue("default")), "default");
    }

    public void testShouldReturnValueWithoutOrDefaultNamespace() {
        assertEquals(I18Next.getInstance().t("app.name"), "i18next");
    }

    public void testShouldReturnValueWithNamespace() {
        assertEquals(I18Next.getInstance().t("specific:app.name"), "i18nextspecific");
    }

    public void testShouldReturnValueMultiKey() {
        assertEquals(I18Next.getInstance().t("notexist", "app.name"), "i18next");
    }

    public void testShouldReturnValueInterpolation() {
        assertEquals(I18Next.getInstance().t("app.insert", new Operation.Interpolation("youAre", "great")), "you are great");
    }

    public void testShouldReturnValueSprintF() {
        assertEquals(I18Next.getInstance().t("app.sprintf", new Operation.SPrintF("a", "b", "c", "d")), "a, b, c and d");
    }

    public void testShouldReturnValueSprintFWithNumber() {
        assertEquals(I18Next.getInstance().t("app.sprintf2", new Operation.SPrintF(1, 1.2f)), String.format("%d %f", 1, 1.2f));
        assertEquals(I18Next.getInstance().t("app.sprintf2", new Operation.SPrintF(1, 1.2d)), String.format("%d %f", 1, 1.2f));
    }

    public void testShouldReturnValuePlural() {
        assertEquals(I18Next.getInstance().t("app.child", new Operation.Plural(1)), "1 child");
        assertEquals(I18Next.getInstance().t("app.child", new Operation.Plural(3)), "3 children");
        assertEquals(I18Next.getInstance().t("app.child2", new Operation.Plural(1)), "a child");
        assertEquals(I18Next.getInstance().t("app.child2", new Operation.Plural(3)), "some children");
    }

    public void testShouldReturnValueNesting() {
        assertEquals(I18Next.getInstance().t("app.district"), "District 9 is more fun than Area 51");
    }

    public void testShouldReturnValueContext() {
        assertEquals(I18Next.getInstance().t("app.friend_context", new Operation.Context("")), "A friend");
        assertEquals(I18Next.getInstance().t("app.friend_context", new Operation.Context("male")), "A boyfriend");
        assertEquals(I18Next.getInstance().t("app.friend_context", new Operation.Context("female")), "A girlfriend");
    }
}
