# Star Maker & Star Maker Framework

StarMakerFramework is an Android library designed to create and manage a collection of star objects. It allows you to add stars with different properties like size, color, and brightness to an array. When the array reaches its capacity, it alerts the user that the sky is full. The library also includes functionality to display a notification with the count of stars when the application goes into the background.

![In-App screenshot with background](https://github.com/mergencdev/StarMaker/blob/main/banner/insider_t_banner.png)



## Getting Started

To get started with StarMakerFramework, follow these steps:

### Prerequisites

- Android Studio
- Min SDK Version 21
- Target SDK Version 30+

### Installation

1. Clone the repository or download the source code.
2. Open the project in Android Studio.
3. Build the project to resolve all dependencies.

### Usage

To use the framework in your Android app, follow these steps:

```kotlin
// Initialize the framework in your MainActivity or any Activity
val starMakerFramework = StarMakerFramework(this)

// To add a star
starMakerFramework.addStarInterface(Size.S) // For a small star
starMakerFramework.addStarInterface(Size.B)   // For a big star
```

### Desired features of the project

- Technologies that we want to be made mandatory in the projects and that we expect you to use:

        ✅1. Create a framework using Java/Kotlin
            a. Make it deployable only for Android.
        ✅2. Create a webview add make "https://img.etimg.com/thumb/msid-72948091,width-650,imgsize-95069,,resizemode-4,quality-100/star_istock.jpg" its source.
        ✅3. Add 3 buttons to the bottom of the webview:
            a. Button 1 – Button title: Small Star
            b. Button 2 – Button title: Big Star
            c. Button 3 – Button title: Reset
            d. Background color of buttons can be anything but black, leave it to your taste.
        ✅4. When I click on the Small/Big Star, a new item should be added to the arrayof star objects. If the array contains more than 10 objects, it should alert "Sky is full".
        ✅5. Star object description is as follows:
            a. It has properties: "Size", "Color", "Brightness"
            b. When adding a small star
              i. size will be "S"
              ii. color will be random from "Red", "Blue" or "Green"
              iii. brightness will be random from "Bright" or "Not so much"
            c. When adding a big star
              i. size will be "B"
              ii. color will be random from "Yellow", "Purple" or "Gray"
              iii. brightness will be random from "Bright" or "Not so much"
        ✅6. When I click Reset, should be removed all items from the array.
        ✅7. Additionally, anytime a star/reset button is clicked I want to see the whole array printed in the console, and another log for how many "Bright" stars are there in the array.
        ✅8. Make only the "addStarInterface" method visible for Framework users. Framework will be used both in Simulator and Real Device, make it work accordingly
        ✅9. When the application is in the background and kill state, the last data of the array should be kept and the user should be able to continue from where left off when the application is in foreground status again.
        ✅10.Add a demo app project that initializes and uses this framework.

- Topics that will provide additional points if you use them in projects:
        
        ✅1. 5 seconds after the application goes into the background or kill state, a notification showing the number of data in the array should appear in the notification center and it should be able to delete the elements in the array via the notification.
            a. This notification should be removed from the notification center when the application goes into the foreground state.
        ✅2. Write in accordance with the code standards of the project you will prepare.
            a. Rules such as modular structure & styling should be considered.
        ✅3. Add a doc comment for the methods you will create.
        ✅4. Unit test.
  
## Source notes I took while developing :heart:
    https://medium.com/android-t%C3%BCrkiye/kotlin-enum-ve-sealed-clases-kullan%C4%B1m%C4%B1-3a9466dc124a
    https://kotlinlang.org/docs/enum-classes.html
    https://kotlinlang.org/docs/visibility-modifiers.html#class-members
    https://www.baeldung.com/kotlin/constants-best-practices
    https://developer.huawei.com/consumer/en/doc/overview/AppGallery-connect
