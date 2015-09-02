# i18next-android

[![Build Status](https://travis-ci.org/i18next/i18next-android.svg)](https://travis-ci.org/i18next/i18next-android)
[![Coverage Status](https://coveralls.io/repos/i18next/i18next-android/badge.svg?service=github)](https://coveralls.io/github/i18next/i18next-android)

i18next-android is a native Android port of [i18next](http://i18next.com/).

----------

## Why?

In order to use the same translated strings for the Web, Android and iOS, we decided to use the i18next features and data formats.

This library is our implementation for Android.
We've been using it in production for a few years.

----------

## Initialization

### Instance

You can use i18next with a new instance:

```java
I18Next i18next = new I18Next();
```

or with a singleton:

```java
I18Next i18next = I18Next.getInstance();
```

### Load data

Start by getting a Loader with:

```java
Loader loader = i18next.loader();
```

After you've set the namespace, language and data, you can apply it by calling:

```java
loader.load();
```

#### Namespace

Specifying the namespace of the file to load:

```java
loader.namespace(String namespace)
```

*Note: if you don't specify a namespace here, the default namespace defined in the Option is taken.*

#### Language

Specifying the language of the file to load:

```java
loader.lang(String lang)
```

*Note: if you don't specify a language here, the default language of your device, or the forced language in the option if defined is taken.*

#### Translation data

Your translations are in a JSON file.
This file can be loaded in different ways:

##### .json file in your ressources

```java
loader.from(Context context, int resource)
```

##### JSON string

```java
loader.from(String json)
```

##### JSONObject

```java
loader.from(JSONObject jsonObject)
```

### Save and Restore previous data

You can save and restore one instance of i18next in the SharedPreferences of your Android device.

```java
i18next.saveInPreference(SharedPreferences sharedPreference)
```

and

```java
i18next.loadFromPreference(SharedPreferences sharedPreference)
```

*Note: the options are not saved in the SharedPreferences.*


----------

## Options

The options of i18next are accessed with:

```java
Options options = i18next.getOptions()
```

You can, for example, enable debug mode (to have more logs) with:

```java
options.setDebugMode(boolean debugMode)
```

You have also access to:
- `default namespace`
- `reuse` prefix and suffix
- `interpolation` prefix and suffix
- `context` prefix and suffix
- `plural` suffix
- `namespace` and `key` separator
- `fallback language`…

----------

## Translation features

Not all features of the javascript implementation of i18next are currently supported.
Here is the list from [the i18next website](http://i18next.com/pages/doc_features.html) and how to use them with this library.

### accessing resources

#### with default namespace

```javascript
// given resourcefile translation.en.json
{
  key1: 'value of key 1'
}
```

Get the value with:

```java
i18n.t("key1"); // -> value of key 1
```

#### with namespace set

```javascript
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
```

Get the value with:

```java
i18n.t("key1"); // -> value of key 1
i18n.t("namespace1.key1"); // -> value of key 1
i18n.t("keys.2"); // -> missing key
i18n.t("namespace2:keys.2"); // -> value of key 2
i18n.t("namespace2:keys.3"); // -> value of key 3
```

#### using multiple keys (first found will be translated)

```javascript
// given resourcefile translation.en.json
{
  key1: 'value of key 1'
}
```

Get the value with:

```java
i18n.t("notExists", "key1"); // -> value of key 1
```

### Multiline in json

```javascript
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
```

**This feature is not implemented in the Android library**

### Arrays in json

```javascript
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
```

**This feature is not implemented in the Android library**

### Providing a default value

```javascript
// given resources
{
  'en-US': { translation: { // key not found } }
};
```

To get a default value if not found:

```java
i18n.t("key", new Operation.DefaultValue("my text")); // -> my text
```

### Nested resources

```javascript
// given resources
{
  dev: { translation: { nesting1: '1 $t(nesting2)' } },
  en: { translation: { nesting2: '2 $t(nesting3)' } },
  'en-US': { translation: {  nesting3: '3' } }
};
```

Get the value:

```java
i18n.t("nesting1"); // -> 1 2 3
```

### Nested resources with option replace

```javascript
// given resources
{
  en: { translation: {
    girlsAndBoys: '$t(girls, {"count": __girls__}) and __count__ boy',
    girlsAndBoys_plural: '$t(girls, {"count": __girls__}) and __count__ boys' },
    girls: '__count__ girl',
    girls_plural: '__count__ girls' } }
};
```

Get the value:

```java
i18n.t("nesting1",
    new Operation.MultiPostProcessing(
        new Operation.Plural(2),
        new Operation.Interpolation("girls", "3"));
// -> 3 girls and 2 boys
```

### Replacing variables

```javascript
// given resources
{
  'en-US': { translation: {  key: '__myVar__ are important' } }
};
```

Get the value:

```java
i18n.t("key", new Operation.Interpolation("myVar", "variables"));  // -> variables are important
```

### Sprintf support

```javascript
// given resources
{
  'en-US': { translation: {
    key1: 'The first 4 letters of the english alphabet are: %s, %s, %s and %s'
  }}
};
```

Get the value:

```java
i18n.t("key1", new Operation.SPrintF('a', 'b', 'c', 'd'));
```

### Simple plural

```javascript
// given resources
{
  'en-US': {
    translation: {
      key: '__count__  child',
      key_plural: '__count__  children'
    }
  }
};
```

Get the values:

```java
i18n.t("key", new Operation.Plural(0)); // -> 0 children
i18n.t("key", new Operation.Plural(1)); // -> 1 child
i18n.t("key", new Operation.Plural(5)); // -> 5 children
```

### Indefinite plural

```javascript
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
```

**This feature is not implemented in the Android library**

### Multiple plural forms

```javascript
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
```

Get the value:

**This feature is not implemented in the Android library**

### Use translation contexts

```javascript
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
```

Get the value:

```java
i18n.t("friend", new Operation.Plural(0)); // -> A friend
i18n.t("friend", new Operation.Context("male")); // -> A boyfriend
i18n.t("friend", new Operation.Context("female")); // -> A girlfriend
```

## Credits

[i18next](http://i18next.com/) and all its contributors.
