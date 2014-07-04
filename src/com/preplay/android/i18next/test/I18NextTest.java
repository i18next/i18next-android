/**
 * 
 */
package com.preplay.android.i18next.test;

import junit.framework.TestCase;

import org.json.JSONException;

import android.annotation.SuppressLint;
import android.test.suitebuilder.annotation.SmallTest;
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

    private String mPreviousDefaultNamespace;
    private String mPreviousFallbackLanguage;

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (mPreviousDefaultNamespace != null) {
            I18Next.getInstance().getOptions().setDefaultNamespace(mPreviousDefaultNamespace);
        }
        if (mPreviousFallbackLanguage != null) {
            I18Next.getInstance().getOptions().setFallbackLanguage(mPreviousFallbackLanguage);
        }
    }

    @Override
    public void setUp() {
        try {
            super.setUp();
        } catch (Exception e) {
            Log.w("TAG", e);
        }
        mPreviousDefaultNamespace = I18Next.getInstance().getOptions().getDefaultNamespace();
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
            // "area2" : "Area 52",
            // "district" : "District 9 is more fun than $t(app.area)",
            // "district_double" : "District 9 is more fun than $t(app.area) and $t(app.area2)",
            // "friend_context" : "A friend",
            // "friend_context_male" : "A boyfriend",
            // "friend_context_female" : "A girlfriend",
            // "pack_of" : "Pack of __count__"
            // }
            // }
            String content = "{\"app\":{\"name\":\"i18next\",\"insert\":\"you are __youAre__\",\"sprintf\":\"%s, %s, %s and %s\",\"sprintf2\":\"%d %f\",\"child\":\"__count__ child\",\"child_plural\":\"__count__ children\",\"child2\":\"a child\",\"child2_plural\":\"some children\",\"area\":\"Area 51\",\"area2\":\"Area 52\",\"district\":\"District 9 is more fun than $t(app.area)\",\"district_double\" : \"District 9 is more fun than $t(app.area) and $t(app.area2)\",\"friend_context\":\"A friend\",\"friend_context_male\":\"A boyfriend\",\"friend_context_female\":\"A girlfriend\",\"pack_of\":\"Pack of __count__\"}}";
            I18Next.getInstance().loader().from(content).namespace("common_test").load();
        } catch (JSONException e) {
            Log.w(TAG, e);
            fail(e.getMessage());
        }
        try {
            String content = "{\"app\":{\"name\":\"i18nextspecific\"}}";
            I18Next.getInstance().loader().from(content).namespace("specific").load();
        } catch (JSONException e) {
            Log.w(TAG, e);
            fail(e.getMessage());
        }
        try {
            String content = "{\"app\":{\"name_on_this_language\":\"i18nextspecific in ZZ\"}}";
            I18Next.getInstance().loader().from(content).lang("zz").namespace("common_test").load();
            I18Next.getInstance().getOptions().setFallbackLanguage("zz");
        } catch (JSONException e) {
            Log.w(TAG, e);
            fail(e.getMessage());
        }
        I18Next.getInstance().getOptions().setDefaultNamespace("common_test");
    }

    @SmallTest
    public void testShouldGetLanguageFallback() {
        assertEquals("i18nextspecific in ZZ", I18Next.getInstance().t("app.name_on_this_language"));
    }

    @SmallTest
    public void testShouldAcceptCandidateKey() {
        String[] listKeyValid = { "test.dot", "test.double.dot", "test.test_underscore", "test.test_double_underscore", "test.test_double_underscore.dot", "test.dot_double_underscore", "test.test2" };
        String[] listKeyInvalid = { "test", "space not accepted", "double..dot" };
        for (String key : listKeyValid) {
            assertTrue("The key '" + key + "' should be accepted as a valid key", I18Next.isI18NextKeyCandidate(key));
        }
        for (String key : listKeyInvalid) {
            assertFalse("The key '" + key + "' should not be accepted as a valid key", I18Next.isI18NextKeyCandidate(key));
        }
    }

    @SmallTest
    public void testShouldReturnValueDefault() {
        assertNull(I18Next.getInstance().t("notexist"));
        assertEquals("default", I18Next.getInstance().t("notexist", new Operation.DefaultValue("default")));
    }

    @SmallTest
    public void testShouldReturnValueWithoutOrDefaultNamespace() {
        assertEquals("i18next", I18Next.getInstance().t("app.name"));
    }

    @SmallTest
    public void testShouldReturnValueWithNamespace() {
        assertEquals("i18nextspecific", I18Next.getInstance().t("specific:app.name"));
    }

    @SmallTest
    public void testShouldReturnValueMultiKey() {
        assertEquals("i18next", I18Next.getInstance().t("notexist", "app.name"));
    }

    @SmallTest
    public void testShouldReturnValueInterpolation() {
        assertEquals("you are great", I18Next.getInstance().t("app.insert", new Operation.Interpolation("youAre", "great")));
    }

    @SmallTest
    public void testShouldReturnValueSprintF() {
        assertEquals("a, b, c and d", I18Next.getInstance().t("app.sprintf", new Operation.SPrintF("a", "b", "c", "d")));
    }

    @SuppressLint("DefaultLocale")
    @SmallTest
    public void testShouldReturnValueSprintFWithNumber() {
        assertEquals(String.format("%d %f", 1, 1.2f), I18Next.getInstance().t("app.sprintf2", new Operation.SPrintF(1, 1.2f)));
        assertEquals(String.format("%d %f", 1, 1.2f), I18Next.getInstance().t("app.sprintf2", new Operation.SPrintF(1, 1.2d)));
    }

    @SmallTest
    public void testShouldReturnValuePlural() {
        assertEquals("1 child", I18Next.getInstance().t("app.child", new Operation.Plural(1)));
        assertEquals("3 children", I18Next.getInstance().t("app.child", new Operation.Plural(3)));
        assertEquals("a child", I18Next.getInstance().t("app.child2", new Operation.Plural(1)));
        assertEquals("some children", I18Next.getInstance().t("app.child2", new Operation.Plural(3)));
    }

    @SmallTest
    public void testShouldReturnValuePluralNotSpecified() {
        assertEquals("Pack of 1", I18Next.getInstance().t("app.pack_of", new Operation.Plural(1)));
        assertEquals("Pack of 3", I18Next.getInstance().t("app.pack_of", new Operation.Plural(3)));
        assertEquals("Pack of 0", I18Next.getInstance().t("app.pack_of", new Operation.Plural(0)));
    }

    @SmallTest
    public void testShouldReturnValueNesting() {
        assertEquals("District 9 is more fun than Area 51", I18Next.getInstance().t("app.district"));
        assertEquals("District 9 is more fun than Area 51 and Area 52", I18Next.getInstance().t("app.district_double"));
    }

    @SmallTest
    public void testShouldReturnValueContext() {
        assertEquals("A friend", I18Next.getInstance().t("app.friend_context", new Operation.Context("")));
        assertEquals("A boyfriend", I18Next.getInstance().t("app.friend_context", new Operation.Context("male")));
        assertEquals("A girlfriend", I18Next.getInstance().t("app.friend_context", new Operation.Context("female")));
    }
}
