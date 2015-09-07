/**
 * 
 */
package com.i18next.android;

import java.util.HashMap;
import java.util.Map;

/**
 * @author stan
 * 
 */
public class Plurals {
    /** Used locally to tag Logs */
    @SuppressWarnings("unused")
    private static final String TAG = Plurals.class.getSimpleName();

    public Map<String, PluralRule> mHashPlurals = new HashMap<String, Plurals.PluralRule>();

    /**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance()
     * or the first access to SingletonHolder.INSTANCE, not before.
     */
    private static class SingletonHolder {
        public static final Plurals INSTANCE = new Plurals();
    }

    public static Plurals getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private Plurals() {
        mHashPlurals.put("ach", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int count) {
                return getInt(count > 1);
            }
        });
        mHashPlurals.put("ach", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int count) {
                return getInt(count > 1);
            }
        });

        mHashPlurals.put("ach", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("af", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ak", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("am", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("an", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ar", new PluralRule(0, 1, 2, 3, 11, 100) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 0 ? 0 : n == 1 ? 1 : n == 2 ? 2 : n % 100 >= 3 && n % 100 <= 10 ? 3 : n % 100 >= 11 ? 4 : 5);
            }
        });
        mHashPlurals.put("arn", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("ast", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ay", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("az", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("be", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("bg", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("bn", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("bo", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("br", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("bs", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("ca", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("cgg", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("cs", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt((n == 1) ? 0 : (n >= 2 && n <= 4) ? 1 : 2);
            }
        });
        mHashPlurals.put("csb", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 1 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("cy", new PluralRule(1, 2, 3, 8) {
            @Override
            protected int getPlurals(int n) {
                return getInt((n == 1) ? 0 : (n == 2) ? 1 : (n != 8 && n != 11) ? 2 : 3);
            }
        });
        mHashPlurals.put("da", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("de", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("dz", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("el", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("en", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("eo", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("es", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("es_ar", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("et", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("eu", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("fa", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("fi", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("fil", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("fo", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("fr", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("fur", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("fy", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ga", new PluralRule(1, 2, 3, 7, 11) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 1 ? 0 : n == 2 ? 1 : n < 7 ? 2 : n < 11 ? 3 : 4);
            }
        });
        mHashPlurals.put("gd", new PluralRule(1, 2, 3, 20) {
            @Override
            protected int getPlurals(int n) {
                return getInt((n == 1 || n == 11) ? 0 : (n == 2 || n == 12) ? 1 : (n > 2 && n < 20) ? 2 : 3);
            }
        });
        mHashPlurals.put("gl", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("gu", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("gun", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("ha", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("he", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("hi", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("hr", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("hu", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("hy", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ia", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("id", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("is", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 != 1 || n % 100 == 11);
            }
        });
        mHashPlurals.put("it", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ja", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("jbo", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("jv", new PluralRule(0, 1) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 0);
            }
        });
        mHashPlurals.put("ka", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("kk", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("km", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("kn", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ko", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("ku", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("kw", new PluralRule(1, 2, 3, 4) {
            @Override
            protected int getPlurals(int n) {
                return getInt((n == 1) ? 0 : (n == 2) ? 1 : (n == 3) ? 2 : 3);
            }
        });
        mHashPlurals.put("ky", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("lb", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ln", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("lo", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("lt", new PluralRule(1, 2, 10) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("lv", new PluralRule(0, 1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n != 0 ? 1 : 2);
            }
        });
        mHashPlurals.put("mai", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("mfe", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("mg", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("mi", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("mk", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 1 || n % 10 == 1 ? 0 : 1);
            }
        });
        mHashPlurals.put("ml", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("mn", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("mnk", new PluralRule(0, 1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 0 ? 0 : n == 1 ? 1 : 2);
            }
        });
        mHashPlurals.put("mr", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ms", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("mt", new PluralRule(1, 2, 11, 20) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 1 ? 0 : n == 0 || (n % 100 > 1 && n % 100 < 11) ? 1 : (n % 100 > 10 && n % 100 < 20) ? 2 : 3);
            }
        });
        mHashPlurals.put("nah", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("nap", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("nb", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ne", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("nl", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("nn", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("no", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("nso", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("oc", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("or", new PluralRule(2, 1) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("pa", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("pap", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("pl", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 1 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("pms", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ps", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("pt", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("pt_br", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("rm", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ro", new PluralRule(1, 2, 20) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n == 1 ? 0 : (n == 0 || (n % 100 > 0 && n % 100 < 20)) ? 1 : 2);
            }
        });
        mHashPlurals.put("ru", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("sah", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("sco", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("se", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("si", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("sk", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt((n == 1) ? 0 : (n >= 2 && n <= 4) ? 1 : 2);
            }
        });
        mHashPlurals.put("sl", new PluralRule(5, 1, 2, 3) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 100 == 1 ? 1 : n % 100 == 2 ? 2 : n % 100 == 3 || n % 100 == 4 ? 3 : 0);
            }
        });
        mHashPlurals.put("so", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("son", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("sq", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("sr", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("su", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("sv", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("sw", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("ta", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("te", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("tg", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("th", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("ti", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("tk", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("tr", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("tt", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("ug", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("uk", new PluralRule(1, 2, 5) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n % 10 == 1 && n % 100 != 11 ? 0 : n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ? 1 : 2);
            }
        });
        mHashPlurals.put("ur", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("uz", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("vi", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("wa", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n > 1);
            }
        });
        mHashPlurals.put("wo", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
        mHashPlurals.put("yo", new PluralRule(1, 2) {
            @Override
            protected int getPlurals(int n) {
                return getInt(n != 1);
            }
        });
        mHashPlurals.put("zh", new PluralRule(1) {
            @Override
            protected int getPlurals(int n) {
                return 0;
            }
        });
    }

    private int getInt(boolean value) {
        return value ? 1 : 0;
    }

    private int getInt(int value) {
        return value;
    }

    public int get(String lng, int count) {
        String[] parts = lng.split("_");
        PluralRule rule = mHashPlurals.get(parts[0]);
        if (rule != null) {
            int var = rule.getPlurals(count);
            int number = rule.mNumbers[var];
            if (rule.mNumbers.length == 2 && rule.mNumbers[0] == 1) {
                if (number == 2) {
                    number = -1; // regular plural
                } else if (number == 1) {
                    number = 1; // singular
                }
            }
            return number;
        } else {
            return count == 1 ? 1 : -1;
        }
    }

    private static abstract class PluralRule {
        private int[] mNumbers;

        public PluralRule(int... numbers) {
            mNumbers = numbers;
        }

        protected abstract int getPlurals(int n);
    }
}
