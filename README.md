<h1 align="center">
  Foodies Food cart app
</h1>

<p align="center">
  <a href="https://github.com/ubisofter">
    <img alt="Foodies Food cart app" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/f8e7031f-2c8f-4bc9-9e5b-18186d9ef887" width="600" />
  </a>
</p>

a Kotlin-based mobile application using Jetpack Compose, is an adaptive application with animations and Clean Architecture. It includes screens where the user can browse the catalog of dishes, add them to the cart, and apply filters and search for dishes.

## ToDo for next Commit
1) ~~Make the status bar light-themed or make black icons and the time~~
2) ~~Center the search input field~~
3) ~~Links don't work at the end and the button wrong placing~~
4) ~~Make normal deletion from the list in the cart~~
5) Make list receiving via request and add the relevant progressBar under the logo during content loading on splashScreen
6) ~~Make an icon~~
7) ~~Make title autoscroll in ItemScreen~~
8) ~~make dish composition scroll in ItemScreen~~
9) ~~add dish feature tags to ItemScreen~~
10) ~~add transition from CartScreen to ItemScreen for detailed info~~
11) Do something with animations from Cart to CelebrateScreen, bad transitions there

## Tech task screenshoots
<img width="230" alt="2" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/5ec68abc-20cf-450d-bc08-ed871f24d323">
<img width="230" alt="3" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/e4a87eed-c2f5-47f3-bdbf-1a4adb555abe">
<img width="230" alt="4" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/18c8811d-6dd0-40f3-9958-adf0fc8530b0">
<img width="230" alt="5" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/1d61d536-e479-4ef1-bf15-435306cdbb9d">
<img width="230" alt="6" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/36bddf3f-f7b5-40b7-9766-f206b077bf4e">
<img width="230" alt="7" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/647f22dd-c16f-42b7-8db0-b6caf2092751">
<img width="230" alt="8" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/676a7151-107d-4975-86c5-abb234aabb46">
<img width="230" alt="9" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/3cb11567-4cd5-480c-98b1-be94fa3461eb">
<img width="230" alt="10" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/9e7a1b62-7a7d-41d5-a1a3-35c7c39c29fb">
<img width="230" alt="11" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/19179458-0571-44ab-ad51-e730a23903e0">
<img width="230" alt="12" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/416cad7c-3898-4b1e-8ac9-aa7101b15479">
<img width="230" alt="13" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/d8038252-b001-4e55-a582-e30ca2339fe9">
<img width="230" alt="14" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/b04aa881-1323-4dc6-a449-666e65b9780d">
<img width="230" alt="15" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/020230dc-3059-42dc-946e-722c33e73c9c">

## Tech Description

Development to be done in Kotlin language using Jetpack Compose

Required:
1. A working adaptive application in portrait orientation
2. animations 
3. Clean Architecture

Prohibited:
1. Screen layout using xml, only individual View are allowed, which analogs are not available in Jetpack Compose.

Welcomed:
1. Comments in code
2. README file, in which you describe the information you consider necessary.(For example, what worked, what didn't work, where there is a bug and similar).
3. Any features you have. (Specify in the README of the repository)
4. Covering the code with tests.

References:
Figma link

UPD: My fault:D I've read tech document before this line and though that it's all task description,
but it was only first page, full task I'll add later, that's why I made decomposition, full document
I've read only after 3 days of development %D

## Task decomposition

**SplashScreen**
```bash
bg colors Primary #F15412 and logo in the center with Lottie animation,
at the end of the animation there should be a transition to the CatalogueFragment.
```
**CatalogueFragment**:
```bash
1. top Toolbar: left filter icon, right search icon, center logo.
2. Row slider under Toolbar with categories from JSON category file as CardView with
text inside (Rolls, Sushi, Sets, Hot, etc).
3. LazyVerticalGrid under the Slider and to the bottom of the screen with products
in the form of GridView in two columns with CardView-cards each card consists of:
dish tag (upper left corner), dish picture (size 2/3 of the card), dish name, weight
(grams), add to cart button with the price of the dish (by clicking on the button
should display a counter with the amount of this dish added to the cart in the format
"- amount +", if the amount is less than 1, then return the button with the price).
4. At the very bottom of the screen after the first click on one of the dishes should
display a button with the total sum of prices for dishes that are added to the cart.
And if there are no dishes in the cart - the button should disappear again.
```

**ItemFragment**: When you click not on a button in the LazyVerticalGrid, but on the CardView card itself, it should open a dish fragment consisting of:
```bash
1. FAB to the top left - back (exit to CatalogueFragment)
2. A picture of the selected dish on half of the screen
3. Under the picture the name of the selected dish 34sp
4. Under the name of the composition of the selected dish 16sp
5. Under the composition ListView with variables (left) and their
values (right), the list of variables includes Weight, Energy, Protein, Fats, Carbohydrates.
6. At the very bottom is the Primary color button "To Cart for (price of selected dish) ₽"
```

**CartFragment** consists of:
```bash
1. Toolbar with a back button of Primary color, and ToolbarTitle "Cart"
2. Under the Toolbar should be RecyclerView with dishes added to the cart,
each dish in this list consists of (from left to right): picture, dish name,
quantity counter with buttons "- quantity +", price (if there is an old price
or discount for this dish, then cross out the old price and above it display the new price)
3. if there is at least one dish in the cart - display the button with
the text "Order for (sum of prices of all dishes in the cart) ₽".
If there are no items in the cart - hide RecyclerView and display
the text in the center "Empty, select dishes in the catalog :)"
```

**FilterSnackBarFragment** (icon in CatalogueFragment), edges are rounded, when you click on the icon, it moves out from the bottom of the screen and darkens everything left on the back fragments, consists of (from top to bottom):
```bash
1. filterTitle "Select dishes" and ListView with three items (Meatless, Spicy, Discount),
each item ListView consists of Item Name (left) and checkbox (right),
when selecting one of the items, dishes that do not contain this item
in the GridView should not be shown, also in the CatalogueFragment above the filter icon
on the left should be a circle with a number of selected items in the filter.
```

**The search**
```bash
icon is responsible for searching for dishes in the catalog, by clicking on it
(if the input field is empty) we hide the GridView with dishes and display the text
"Enter the name of the dish you are looking for" in the center, and when the user
starts writing a query in the input field, we check the database for matches by name,
if we don't find matches by name, we look for a request in the composition of dishes,
if we didn't find a match in the composition of dishes or with filters enabled
there are no suitable dishes - we hide the GridView and write in the center
"Nothing found : (", if there is nothing on the request of the person and he hid the search
by pressing the back button - then hide the GridView and write in the center
"There are no such dishes :(\nTry to change the filters".
```

## 💻 Tech Stack

- Kotlin
- Jetpack Compose
- Other stack wil be added later..

## Project structure

- in Development..

## Build and run

- Here will be instruction..

## Usage

- in Development..

## Author

- **[Ubisofter](https://github.com/ubisofter)**

## 🍰 Contribution
If you'd like to contribute to the Foodies App, feel free to create a pull request. I'm welcome any contributions!

## 📃 Apache 2.0 License 

Copyright 2023 by Ubisofter

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

## Created with ❤️ and few wok's.
