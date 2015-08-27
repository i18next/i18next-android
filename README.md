# i18next-android

[![Build Status](https://travis-ci.org/preplay/i18next-android.svg)](https://travis-ci.org/preplay/i18next-android)

i18next-android is an Android portage of the library [i18next](http://i18next.com/).


----------


## Why?

In order to use the same translations for the Web, Android and iOS, we decide to use the i18next protocol.

This library is our implementation for Android, we use in production since many years.


----------


## Initialization

### Instance

You can use i18next with a new instance:

    I18Next i18next = new I18Next();

or with a singleton:

    I18Next i18next = I18Next.getInstance();

### Load data

Start by get a Loader with:

    Loader loader = i18next.loader();

After your set the namespace, the language and the datas, you can apply the load by calling:

    loader.load();

#### Namespace

To specify the namespace of the file that will be load:

    loader.namespace(String namespace)

*Note: if you don't specify a namespace here, we will take the default namespace defined into the Option.*

#### Language

To specify the language of the file that will be load:

    loader.lang(String lang)

*Note: if you don't specify a language here, we will take the default language of your device, or the force language into the option if define.*

#### Translation datas
Your translations are into a JSON file.
This file can be load by different ways:

##### .json file into your ressources

    loader.from(Context context, int resource)

##### JSON string

    loader.from(String json)

##### JSONObject

    loader.from(JSONObject jsonObject)

### Save and Restore previous data

You can save and restore one instance of i18next into the SharedPreferences of your Android device.

    i18next.saveInPreference(SharedPreferences sharedPreference)

and

    i18next.loadFromPreference(SharedPreferences sharedPreference)

*Note: the options are not saved into the SharedPreferences.*


----------

## Options

The option of i18next can be access with:

    Options options = i18next.getOptions()

You can, for example, set the debug mode (to have more logs) with:

    options.setDebugMode(boolean debugMode)

You have also access to:
- ```default namespace```
- ```reuse``` prefix and suffix
- ```interpolation``` prefix and suffix
- ```context``` prefix and suffix
- ```plural``` suffix
- ```namespace``` and ```key``` separator
- ```fallback language```…

----------

## Translation features

Not all features of the i18next are currently supported.
Here is the list from [the i18next website](http://i18next.com/pages/doc_features.html) and how to do it with this library.

### accessing resources

#### with default namespace

    // given resourcefile translation.en.json
    {
      key1: 'value of key 1'
    }

Get the value with:

    i18n.t("key1"); // -> value of key 1

#### with namespace set

    // given resourcesfile namespace1.en.json (default ns)
    {
       key1: 'value of key 1'
    }

    // given additional resourcesfile namespace2.en.json
    {
      keys: {
        2: 'value of key 2',
        3: 'value of key 3'
      }
    }

Get the value with:

    i18n.t("key1"); // -> value of key 1
    i18n.t("namespace1.key1"); // -> value of key 1
    i18n.t("keys.2"); // -> missing key
    i18n.t("namespace2:keys.2"); // -> value of key 2
    i18n.t("namespace2:keys.3"); // -> value of key 3

#### using multiple keys (first found will be translated)

    // given resourcefile translation.en.json
    {
      key1: 'value of key 1'
    }

Get the value with:

    i18n.t("notExists", "key1"); // -> value of key 1

### Multiline in json

    // given resources in arabic
    {
      'en-US': {
        translation: {
          key: [
            "line1",
            "line2",
            "line3"
          ]
        }
      }
    };

**This feature is not implemented into the Android library**

### Arrays in json
    // given resources in arabic
    {
      'en-US': {
        translation: {
          people: [
            { name: "tom" },
            { name: "steve" }
          ]
        }
      }
    };
**This feature is not implemented into the Android library**

### Providing a default value

    // given resources
    {
      'en-US': { translation: { // key not found } }
    };

To get a default value if not found:

    i18n.t("key", new Operation.DefaultValue("my text")); // -> my text

### Nested resources

    // given resources
    {
      dev: { translation: { nesting1: '1 $t(nesting2)' } },
      en: { translation: { nesting2: '2 $t(nesting3)' } },
      'en-US': { translation: {  nesting3: '3' } }
    };

Get the value:

    i18n.t("nesting1"); // -> 1 2 3

### Nested resources with option replace

    // given resources
    {
      en: { translation: {
        girlsAndBoys: '$t(girls, {"count": __girls__}) and __count__ boy',
        girlsAndBoys_plural: '$t(girls, {"count": __girls__}) and __count__ boys' },
        girls: '__count__ girl',
        girls_plural: '__count__ girls' } }
    };

Get the value:

    i18n.t("nesting1",
        new Operation.MultiPostProcessing(
            new Operation.Plural(2),
            new Operation.Interpolation("girls", "3"));
    // -> 3 girls and 2 boys

### Replacing variables

    // given resources
    {
      'en-US': { translation: {  key: '__myVar__ are important' } }
    };

Get the value:

  i18n.t("key", new Operation.Interpolation("myVar", "variables"));  // -> variables are important

### Sprintf support

    // given resources
    {
      'en-US': { translation: {
        key1: 'The first 4 letters of the english alphabet are: %s, %s, %s and %s'
      }}
    };

Get the value:

  i18n.t("key1", new Operation.SPrintF('a', 'b', 'c', 'd'));

### Simple plural

    // given resources
    {
      'en-US': {
        translation: {
          key: '__count__  child',
          key_plural: '__count__  children'
        }
      }
    };

Get the values:

    i18n.t("key", new Operation.Plural(0)); // -> 0 children
    i18n.t("key", new Operation.Plural(1)); // -> 1 child
    i18n.t("key", new Operation.Plural(5)); // -> 5 children

### Indefinite plural

    // given resources
    {
      'en-US': {
        translation: {
          key: '__count__  child',
          key_plural: '__count__  children',
          key_indefinite: 'a child',
          key_plural_indefinite: 'some children'
        }
      }
    };

**This feature is not implemented into the Android library**

### Multiple plural forms

    // given resources in arabic
    {
      'ar': {
        translation: {
          key: 'singular',
          key_plural_0: 'zero',
          key_plural_2: 'two',
          key_plural_3: 'few',
          key_plural_11: 'many',
          key_plural_100: 'plural'
        }
      }
    };

Get the value:

**This feature is not implemented into the Android library**

### Use translation contexts

    // given resources
    {
      'en-US': {
        translation: {
          friend: 'A friend',
          friend_male: 'A boyfriend',
          friend_female: 'A girlfriend'
        }
      }
    };

Get the value:

    i18n.t("friend", new Operation.Plural(0)); // -> A friend
    i18n.t("friend", new Operation.Context("male")); // -> A boyfriend
    i18n.t("friend", new Operation.Context("female")); // -> A girlfriend

