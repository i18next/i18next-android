package com.i18next.android;

import android.annotation.SuppressLint;
import android.util.Log;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class I18NextTest {

    private static final String TAG = I18NextTest.class.getSimpleName();

    private String mPreviousDefaultNamespace;

    private String mPreviousFallbackLanguage;

    @Before
    public void setUp() throws Exception {
        mPreviousDefaultNamespace = I18Next.getInstance().getOptions().getDefaultNamespace();
        mPreviousFallbackLanguage = I18Next.getInstance().getOptions().getFallbackLanguage();
        try {
            String content = "{"
                    + "  \"app\": {"
                    + "    \"name\": \"i18next\","
                    + "    \"insert\": \"you are __youAre__\","
                    + "    \"sprintf\": \"%s, %s, %s and %s\","
                    + "    \"sprintf2\": \"%d %f\","
                    + "    \"child\": \"__count__ child\","
                    + "    \"child_plural\": \"__count__ children\","
                    + "    \"child2\": \"a child\","
                    + "    \"child2_plural\": \"some children\","
                    + "    \"area\": \"Area 51\","
                    + "    \"area2\": \"Area 52\","
                    + "    \"district\": \"District 9 is more fun than $t(app.area)\","
                    + "    \"district_double\": \"District 9 is more fun than $t(app.area) and $t(app.area2)\","
                    + "    \"friend_context\": \"A friend\","
                    + "    \"friend_context_male\": \"A boyfriend\","
                    + "    \"friend_context_female\": \"A girlfriend\","
                    + "    \"pack_of\": \"Pack of __count__\","
                    + "    \"replace_before\": \"$t(app.__param__)\","
                    + "    \"replace_int\": \"$t(app.__param2__)\","
                    + "    \"replace_after\": \"__param3__\","
                    + "    \"replace_with_count\": \"$t(app.child, {\\\"count\\\": 5})\","
                    + "    \"replace_with_count_and_replace\": \"$t(app.child, {\\\"count\\\": __param__})\""
                    + "  }"
                    + "}";
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

    @After
    public void tearDown() throws Exception {
        if (mPreviousDefaultNamespace != null) {
            I18Next.getInstance().getOptions().setDefaultNamespace(mPreviousDefaultNamespace);
        }
        if (mPreviousFallbackLanguage != null) {
            I18Next.getInstance().getOptions().setFallbackLanguage(mPreviousFallbackLanguage);
        }
    }

    @Test
    public void shouldGetLanguageFallback() {
        assertEquals("i18nextspecific in ZZ", I18Next.getInstance().t("app.name_on_this_language"));
    }

    @Test
    public void shouldAcceptCandidateKey() {
        String[] listKeyValid = {"test.dot", "test.double.dot", "test.test_underscore",
                "test.test_double_underscore", "test.test_double_underscore.dot",
                "test.dot_double_underscore", "test.test2"};
        String[] listKeyInvalid = {"test", "space not accepted", "double..dot"};
        for (String key : listKeyValid) {
            assertTrue("The key '" + key + "' should be accepted as a valid key",
                    I18Next.isI18NextKeyCandidate(key));
        }
        for (String key : listKeyInvalid) {
            assertFalse("The key '" + key + "' should not be accepted as a valid key",
                    I18Next.isI18NextKeyCandidate(key));
        }
    }

    @Test
    public void shouldReturnValueDefault() {
        assertNull(I18Next.getInstance().t("notexist"));
        assertEquals("default",
                I18Next.getInstance().t("notexist", new Operation.DefaultValue("default")));
    }

    @Test
    public void shouldReturnValueAfterMultipleReplacementAndNesting() {
        String resFinal = "fin";
        Map<String, Object> replacements = new HashMap<String, Object>();
        replacements.put("param", "replace_int");
        replacements.put("param2", "replace_after");
        replacements.put("param3", resFinal);
        assertEquals(resFinal, I18Next.getInstance()
                .t("app.replace_before", new Operation.Interpolation(replacements)));
    }

    @Test
    public void shouldReturnValueAfterReplacementNestingAndCount() {
        assertEquals("5 children", I18Next.getInstance().t("app.replace_with_count"));
        assertEquals("11 children", I18Next.getInstance().t("app.replace_with_count_and_replace",
                new Operation.Interpolation("param", "11")));
    }

    @Test
    public void shouldReturnValueWithoutOrDefaultNamespace() {
        assertEquals("i18next", I18Next.getInstance().t("app.name"));
    }

    @Test
    public void shouldReturnValueWithNamespace() {
        assertEquals("i18nextspecific", I18Next.getInstance().t("specific:app.name"));
    }

    @Test
    public void shouldReturnValueMultiKey() {
        assertEquals("i18next", I18Next.getInstance().t("notexist", "app.name"));
    }

    @Test
    public void shouldReturnValueInterpolation() {
        assertEquals("you are great", I18Next.getInstance()
                .t("app.insert", new Operation.Interpolation("youAre", "great")));
    }

    @Test
    public void shouldReturnValueSprintF() {
        assertEquals("a, b, c and d",
                I18Next.getInstance().t("app.sprintf", new Operation.SPrintF("a", "b", "c", "d")));
    }

    @SuppressLint("DefaultLocale")
    @Test
    public void shouldReturnValueSprintFWithNumber() {
        assertEquals(String.format("%d %f", 1, 1.2f),
                I18Next.getInstance().t("app.sprintf2", new Operation.SPrintF(1, 1.2f)));
        assertEquals(String.format("%d %f", 1, 1.2f),
                I18Next.getInstance().t("app.sprintf2", new Operation.SPrintF(1, 1.2d)));
    }

    @Test
    public void shouldReturnValuePlural() {
        assertEquals("1 child", I18Next.getInstance().t("app.child", new Operation.Plural(1)));
        assertEquals("3 children", I18Next.getInstance().t("app.child", new Operation.Plural(3)));
        assertEquals("a child", I18Next.getInstance().t("app.child2", new Operation.Plural(1)));
        assertEquals("some children",
                I18Next.getInstance().t("app.child2", new Operation.Plural(3)));
    }

    @Test
    public void shouldReturnValuePluralNotSpecified() {
        assertEquals("Pack of 1", I18Next.getInstance().t("app.pack_of", new Operation.Plural(1)));
        assertEquals("Pack of 3", I18Next.getInstance().t("app.pack_of", new Operation.Plural(3)));
        assertEquals("Pack of 0", I18Next.getInstance().t("app.pack_of", new Operation.Plural(0)));
    }

    @Test
    public void shouldReturnValueNesting() {
        assertEquals("District 9 is more fun than Area 51",
                I18Next.getInstance().t("app.district"));
        assertEquals("District 9 is more fun than Area 51 and Area 52",
                I18Next.getInstance().t("app.district_double"));
    }

    @Test
    public void shouldReturnValueContext() {
        assertEquals("A friend",
                I18Next.getInstance().t("app.friend_context", new Operation.Context("")));
        assertEquals("A boyfriend",
                I18Next.getInstance().t("app.friend_context", new Operation.Context("male")));
        assertEquals("A girlfriend",
                I18Next.getInstance().t("app.friend_context", new Operation.Context("female")));
    }
}
