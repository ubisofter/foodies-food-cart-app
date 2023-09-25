<a name="english-version"></a>
## EN

<h1 align="center">
  Foodies Food cart app
</h1>

<p align="center">
<a href="https://github.com/ubisofter/foodies-food-cart-app/blob/master/LICENSE" target="blank">
<img src="https://img.shields.io/github/license/ubisofter/foodies-food-cart-app?style=flat-square" alt="foodies-food-cart-app license" />
</a>
<a href="https://github.com/ubisofter/foodies-food-cart-app/fork" target="blank">
<img src="https://img.shields.io/github/forks/ubisofter/foodies-food-cart-app?style=flat-square" alt="foodies-food-cart-app forks"/>
</a>
<a href="https://github.com/ubisofter/foodies-food-cart-app/stargazers" target="blank">
<img src="https://img.shields.io/github/stars/ubisofter/foodies-food-cart-app?style=flat-square" alt="foodies-food-cart-app stars"/>
</a>
<a href="https://github.com/ubisofter/foodies-food-cart-app/issues" target="blank">
<img src="https://img.shields.io/github/issues/ubisofter/foodies-food-cart-app?style=flat-square" alt="foodies-food-cart-app issues"/>
</a>
<a href="https://github.com/ubisofter/foodies-food-cart-app/pulls" target="blank">
<img src="https://img.shields.io/github/issues-pr/ubisofter/foodies-food-cart-app?style=flat-square" alt="foodies-food-cart-app pull-requests"/>
</a>
</p>

<p align="center">
<a href="https://github.com/ubisofter">
<img width="300" alt="3" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/e4a87eed-c2f5-47f3-bdbf-1a4adb555abe">
<img alt="Foodies Food cart app" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/f8e7031f-2c8f-4bc9-9e5b-18186d9ef887" width="600" />
</a>

<p align="center">
    <a href="https://github.com/ubisofter/foodies-food-cart-app#russian-version">RU</a>
    ·
    <a href="https://github.com/ubisofter/foodies-food-cart-app#english-version">EN</a>
</p>

a Kotlin-based mobile application using Jetpack Compose, is an adaptive application with animations and Clean Architecture. It includes screens where the user can browse the catalog of dishes, add them to the cart, and apply filters and search the catalogue.

## ToDo for next Commit
1) ~~Make the status bar light-themed or make black icons and the time~~
2) ~~Center the search input field~~
3) ~~Links don't work at the end and the button wrong placing~~
4) ~~Make normal deletion from the list in the cart~~
5) ~~Make list receiving via request (partly)~~  and add the relevant progressBar under the logo during content loading on splashScreen
6) ~~Make an icon~~
7) ~~Make title autoscroll in ItemScreen~~
8) ~~make dish composition scroll in ItemScreen~~
9) ~~add dish feature tags to ItemScreen~~
10) ~~add transition from CartScreen to ItemScreen for detailed info~~
11) Do something with animations from Cart to CelebrateScreen, bad transitions there

## Tech task screenshoots
<img width="200" alt="2" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/5ec68abc-20cf-450d-bc08-ed871f24d323">
<img width="200" alt="3" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/e4a87eed-c2f5-47f3-bdbf-1a4adb555abe">
<img width="200" alt="4" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/18c8811d-6dd0-40f3-9958-adf0fc8530b0">
<img width="200" alt="5" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/1d61d536-e479-4ef1-bf15-435306cdbb9d">
<img width="200" alt="6" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/36bddf3f-f7b5-40b7-9766-f206b077bf4e">
<img width="200" alt="7" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/647f22dd-c16f-42b7-8db0-b6caf2092751">
<img width="200" alt="8" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/676a7151-107d-4975-86c5-abb234aabb46">
<img width="200" alt="9" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/3cb11567-4cd5-480c-98b1-be94fa3461eb">
<img width="200" alt="10" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/9e7a1b62-7a7d-41d5-a1a3-35c7c39c29fb">
<img width="200" alt="11" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/19179458-0571-44ab-ad51-e730a23903e0">
<img width="200" alt="12" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/416cad7c-3898-4b1e-8ac9-aa7101b15479">
<img width="200" alt="13" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/d8038252-b001-4e55-a582-e30ca2339fe9">

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
...

UPD: My fault:D I've read tech document until this line and though that it's all task description,
but it was only first page, full task I'll add later, that's why I made decomposition, full document
I've read only after 3 days of development %D - **[Full tech description](https://docs.google.com/document/d/1_GwEDzsLOt1jEllWbIvKGRz88OHj5SnTmXF-CCwwS2w/edit)**

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
- Jetpack Compose: To create the user interface of an application.
- Navigation Compose: To manage navigation between fragments and screens.
- GSON: To work with data in JSON format.
- Retrofit: For making network requests to the server.
- Kotlin Coroutines: For asynchronous task processing and thread management.
- Lottie: For displaying Lottie animations.
- Android Architecture Components (ViewModel, LiveData): For managing data lifecycle and business logic.
- Material Icons: For using icons in the user interface.
- JUnit and Espresso: For writing unit tests and tool tests.

## Project structure

From the view of the application structure, it involves the separation
into different layers where the business logic and the presentation (UI) layer
are separated and linked using architectural components such as ViewModel.
The UI is implemented using Jetpack Compose, which provides a declarative approach
to UI creation. Even though there are incomplete points of DI and Unit testing,
the application structure attempts to follow the principles of clean architecture
and separation of responsibilities between components.

```bash
MyApp/
├── MainActivity.kt
├── presentation/
│   ├── screens/
│   │   ├── CartScreen.kt
│   │   ├── CatalogueScreen.kt
│   │   ├── CelebrateScreen.kt
│   │   ├── ItemScreen.kt
│   │   └── SplashScreen.kt
│   └── ui/
│       ├── AppThemeFont.kt
│       ├── CartItem.kt
│       ├── CatalogueItem.kt
│       └── CategoryItem.kt
│       └── Slider.kt
├── viewmodel/
│   ├── CartViewModel.kt
│   └── CatalogueViewModel.kt
├── data/
│   ├── Category.kt
│   ├── FoodApi.kt
│   └── Product.kt
├── di/
│   └── AppModule.kt (planned)
│   └── MyApp.kt (planned)
│   └── AppComponent.kt (planned)
└── util/
├── JsonUtils.kt (planned)
└── OtherUtils.kt (planned)
```

## Build and run

To build and run your application on GitHub, follow these steps:

1. Make sure you have Git installed on your computer.

2. Create a local copy of the repository from GitHub if you haven't already done so:
```bash
git clone https://github.com/ubisofter/foodies-food-cart-app.git
```

3. Navigate to the project directory:
```bash
cd foodies-food-cart-app
```

Make sure you have Android Studio and Gradle installed.

4. Open the project in Android Studio:

Launch Android Studio.
Select "File" -> "Open".
Navigate to your project directory and select the build.gradle.kts file.
Connect a physical Android device or start an Android emulator.

Build and run the application:

In Android Studio, select the run configuration (default is app).
Click the Run button or use the Shift + F10 hotkey.
Wait for Android Studio to build the app and install it on your device or emulator.

Your app should now be successfully built and running on your Android device or emulator.

## Usage & Features

Main Features
- Product Catalog: Explore various product categories and choose the ones that interest you.
- Product Viewing: Browse product information, including descriptions, images, prices, and specifications.
- Shopping Cart: Add products to your cart and review them before placing an order.
- Order Placement: Place orders by adding products to your cart.

Side Features
- Adaptive Grid View: Utilizes LazyVerticalGrid for responsive grid layouts on different screen sizes.
- Custom Bottom Sheet: Features a custom-designed bottom sheet with fluid content animations.
- Auto-Scrolling Titles: Long product titles automatically scroll horizontally for an engaging user experience.
- Jetpack Compose Powered: The entire app is built using Jetpack Compose, offering a modern, declarative, and efficient UI development experience.

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

<a name="russian-version"></a>
## RU

<h1 align="center">
  Приложение Foodies Food Cart
</h1>

<p align="center">
  <a href="https://github.com/ubisofter">
    <img alt="Приложение Foodies Еда" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/f8e7031f-2c8f-4bc9-9e5b-18186d9ef887" width="600" />
  </a>
</p>

Приложение для мобильных устройств на языке Kotlin, использующее Jetpack Compose, является адаптивным приложением с анимациями и чистой архитектурой. Оно включает в себя экраны, на которых пользователь может просматривать каталог блюд, добавлять их в корзину, а также применять фильтры и выполнять поиск в каталоге.

## Задачи на следующий коммит
1) ~~Сделать статус-бар светлой темы или сделать черные значки и время~~
2) ~~Центрировать поле ввода для поиска~~
3) ~~Исправить неработающие ссылки в конце и неправильное расположение кнопки~~
4) ~~Реализовать нормальное удаление из списка в корзине~~
5) ~~Реализовать получение списка блюд по запросу _(частично)~~ и добавить соответствующий индикатор выполнения внизу логотипа при загрузке контента на SplashScreen_
6) ~~сделать иконку~~
7) ~~сделать автоскролл тайтла в ItemScreen~~
8) ~~сделать скролл состава блюда в ItemScreen~~
9) ~~добавить теги особенностей блюда в ItemScreen~~
10) _исправить анимацию выпадения диалога на последнем экране_

## Скриншоты тз
<img width="200" alt="2" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/5ec68abc-20cf-450d-bc08-ed871f24d323">
<img width="200" alt="3" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/e4a87eed-c2f5-47f3-bdbf-1a4adb555abe">
<img width="200" alt="4" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/18c8811d-6dd0-40f3-9958-adf0fc8530b0">
<img width="200" alt="5" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/1d61d536-e479-4ef1-bf15-435306cdbb9d">
<img width="200" alt="6" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/36bddf3f-f7b5-40b7-9766-f206b077bf4e">
<img width="200" alt="7" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/647f22dd-c16f-42b7-8db0-b6caf2092751">
<img width="200" alt="8" src="https://ithub.com/ubisofter/foodies-food-cart-app/assets/78037558/676a7151-107d-4975-86c5-abb234aabb46">
<img width="200" alt="9" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/3cb11567-4cd5-480c-98b1-be94fa3461eb">
<img width="200" alt="10" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/9e7a1b62-7a7d-41d5-a1a3-35c7c39c29fb">
<img width="200" alt="11" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/19179458-0571-44ab-ad51-e730a23903e0">
<img width="200" alt="12" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/416cad7c-3898-4b1e-8ac9-aa7101b15479">
<img width="200" alt="13" src="https://github.com/ubisofter/foodies-food-cart-app/assets/78037558/d8038252-b001-4e55-a582-e30ca2339fe9">

## Техническое описание

Разработку проводить на языке Kotlin с использованием Jetpack Compose

Необходимо:
1. Работающее адаптивное в портретной ориентации приложение
2. Анимации
3. Clean Architecture

Запрещено:
1. Верстка экрана с помощью xml, разрешены только отдельные View, аналогов которых нет в Jetpack Compose.

Приветствуется:
1. Комментарии в коде
2. Файл README, в котором вы опишите информацию, которую посчитаете нужной. (К примеру, что получилось, что не очень, где есть баг и подобное)
3. Какие - либо свои фичи. (Указать в README репозитория)
4. Покрытие кода тестами.

Ссылки:
1. На Figma
...

ОБНОВЛЕНИЕ: Моя ошибка: я прочитал техническое задание только до этой строки и подумал, что это вся постановка задачи, но это была только первая страница. Полный текст задачи я добавлю позже, _это причина почему я сделал декомпозицию_. Полный текст я прочитал только после 3 дней разработки %D - [Полное тз](https://docs.google.com/document/d/1_GwEDzsLOt1jEllWbIvKGRz88OHj5SnTmXF-CCwwS2w/edit)

## Декомпозиция задач

**SplashScreen**
```bash
Фоновые цвета - Primary #F15412 и логотип в центре с анимацией Lottie.
По завершении анимации должен происходить переход на CatalogueScreen.
```

**CatalogueScreen**
```bash
1. toolbar с кнопками: иконка фильтра слева, иконка поиска справа, центральный логотип.
2. горизонтальный слайдер под тулбаром с категориями из JSON-файла категорий
в виде карточек с текстом внутри(Роллы, Суши, Сеты, Горячее и др.).
3. Отображение сетки с продуктами (LazyVerticalGrid) под слайдером и до нижней части экрана.
Продукты представлены в виде сетки из двух столбцов с карточками, каждая из которых содержит:
тег блюда (в верхнем левом углу), изображение блюда (размером 2/3 карточки), название блюда,
вес (в граммах), кнопку "Добавить в корзину" с указанием цены (при нажатии на кнопку
должен появиться счетчик количества данного блюда в корзине в формате "- количество +",
если количество меньше 1, кнопка должна скрываться).
4. В самом низу экрана после первого клика на одно из блюд должна появиться кнопка
с общей суммой цен на блюда, добавленные в корзину. Если корзина пуста, кнопка должна скрываться.
```

**ItemScreen**: При клике на карточку карточку в LazyVerticalGrid (не на кнопку),
должен открываться фрагментс блюдом, который состоит из следующего:
```bash
1. FAB (плавающая кнопка) в левом верхнем углу - кнопка "назад" (выход на CatalogueScreen).
2. Изображение выбранного блюда на половину экрана.
3. Под изображением наименование выбранного блюда размером 34sp.
4. Под наименованием состав выбранного блюда размером 16sp.
5. Под составом - список с переменными (слева) и их значениями (справа).
Список переменных включает в себя Вес, Калории, Белки, Жиры, Углеводы.
6. В самом низу кнопка "В корзину за (цена выбранного блюда) ₽" primary цветом.
```

**CartScreen** состоит из:
```bash
1. toolbar с иконкой "назад" основного цвета и заголовком "Корзина".
2. Под панелью должен находиться LVG (список) с добавленными в корзину блюдами.
Каждое блюдо в этом списке состоит из (слева направо): изображение, название блюда,
счетчик количества с кнопками "- количество +" и цена (если есть старая цена или скидка на это блюдо,
то старую цену нужно перечеркивать и над ней отображать новую цену).
3. Если в корзине есть хотя бы одно блюдо, отображается кнопка с текстом
"Заказать за (сумма цен всех блюд в корзине) ₽". Если в корзине нет блюд - LVG скрывается,
а в центре экрана отображается текст "Пусто, выберите блюда в каталоге :)".
```

**BottomSheetFilter** (иконка в toolbar CatalogueScreen). При нажатии на иконку выдвигается снизу экрана
и затемняет весь контент. Состоит из (сверху вниз):
```bash
1. Заголовок "Выберите блюда" и ListView с тремя элементами (Без мяса, Острое, Скидка).
Каждый элемент ListView состоит из названия элемента (слева) и флажка (справа).
При выборе одного из элементов блюда, не содержащие данный элемент, не должны отображаться в LVG.
Также над иконкой фильтра в CatalogueScreen слева должен отображаться Badge
с количеством выбранных элементов в фильтре.
```

**Поиск иконка** 
отвечает за поиск блюд в каталоге. При клике на нее (если поле ввода пустое), мы скрываем LVG
с блюдами и отображаем текст "Введите название блюда, которое вы ищете" по центру экрана.
Когда пользователь начинает вводить запрос в поле ввода, мы проверяем базу данных на соответствие по названию.
Если совпадения по названию не найдены, мы ищем запрос в составе блюд. Если мы не нашли совпадения в составе блюд
или с включенными фильтрами нет подходящих блюд, мы скрываем LVG и пишем по центру "Ничего не найдено :(".
Если нет совпадений по запросу пользователя и он скрыл поиск, нажав кнопку "назад",
то мы скрываем LVG и пишем "Таких блюд нет :(\nПопробуйте изменить фильтры".

## 💻 Технологический стек

- Kotlin
- Jetpack Compose: Для создания пользовательского интерфейса приложения.
- Navigation Compose: Для управления навигацией между фрагментами и экранами.
- GSON: Для работы с данными в формате JSON.
- Retrofit: Для выполнения сетевых запросов к серверу.
- Kotlin Coroutines: Для асинхронной обработки задач и управления потоками.
- Lottie: Для отображения анимаций Lottie.
- Android Architecture Components (ViewModel, LiveData): Для управления жизненным циклом данных и бизнес-логики.
- Material Icons: Для использования иконок в пользовательском интерфейсе.
- JUnit и Espresso: Для написания юнит-тестов и инструментальных тестов.

## Структура проекта
С точки зрения структуры приложения оно разделено на разные уровни, где бизнес-логика и слой представления (UI) разделены и связаны с использованием архитектурных компонентов, таких как ViewModel. Интерфейс пользователя реализован с использованием Jetpack Compose, который предоставляет декларативный подход к созданию пользовательского интерфейса. Несмотря на недоработки в части DI (внедрение зависимостей) и юнит-тестирования, структура приложения стремится следовать принципам чистой архитектуры и разделения обязанностей между компонентами.

```bash
MyApp/
├── MainActivity.kt
├── presentation/
│   ├── screens/
│   │   ├── CartScreen.kt
│   │   ├── CatalogueScreen.kt
│   │   ├── CelebrateScreen.kt
│   │   ├── ItemScreen.kt
│   │   └── SplashScreen.kt
│   └── ui/
│       ├── AppThemeFont.kt
│       ├── CartItem.kt
│       ├── CatalogueItem.kt
│       └── CategoryItem.kt
│       └── Slider.kt
├── viewmodel/
│   ├── CartViewModel.kt
│   └── CatalogueViewModel.kt
├── data/
│   ├── Category.kt
│   ├── FoodApi.kt
│   └── Product.kt
├── di/
│   └── AppModule.kt (планируется)
│   └── MyApp.kt (планируется)
│   └── AppComponent.kt (планируется)
└── util/
├── JsonUtils.kt (планируется)
└── OtherUtils.kt (планируется)
```

## Сборка и запуск
Чтобы собрать и запустить приложение на GitHub, выполните следующие шаги:

1) Убедитесь, что на вашем компьютере установлен Git.

2) Создайте локальную копию репозитория с GitHub, если вы еще этого не сделали:

```bash
git clone https://github.com/ubisofter/foodies-food-cart-app.git
```

3) Перейдите в каталог проекта:

```bash
cd foodies-food-cart-app
```

4) Убедитесь, что на вашем компьютере установлены Android Studio и Gradle.

Откройте проект в Android Studio:
Запустите Android Studio.
Выберите "Open an existing Android Studio project" (Открыть существующий проект Android Studio) в диалоговом окне.
Перейдите в каталог проекта и выберите файл build.gradle в корневом каталоге проекта.
Нажмите "Open" (Открыть).
6) Подключите устройство Android к компьютеру или используйте виртуальное устройство, затем нажмите кнопку "Run" (Запуск) в Android Studio.
Приложение будет установлено и запущено на вашем устройстве или виртуальном устройстве Android.

## Использование и фичи.

**Основные функции:**
- Каталог продуктов: Исследуйте разнообразные категории продуктов и выбирайте те, которые вам интересны.
- Просмотр продуктов: Просматривайте информацию о продуктах, включая описания, изображения, цены и характеристики.
- Корзина: Добавляйте продукты в корзину и просматривайте их перед оформлением заказа.
- Оформление заказа: Оформляйте заказы, добавляя продукты в корзину.

**Дополнительные функции проекта:**
- Адаптивный вид сетки: Использует адаптивную сетку LazyVerticalGrid для отзывчивых макетов сеток на разных размерах экрана.
- кас~~тыльный~~томный BottomSheet: Настраиваемый экран фильтров с плавными анимациями контента.
- Автоматическая прокрутка заголовков: Длинные названия продуктов автоматически прокручиваются горизонтально для хорошего UX.
- Jetpack Compose: Весь проект создан с использованием Jetpack Compose, предоставляя современный, декларативный и эффективный опыт разработки пользовательского интерфейса.

**Для полного счастья не хватает:**
- Регистрация и аутентификация пользователей.
- Возможность оценивать и оставлять отзывы о блюдах.
- Расширенные фильтры для каталога, такие как сортировка по цене, калориям и т. д.
- Возможность добавления пользовательских блюд и рецептов.
- Интеграция с платежными системами для онлайн-заказов.
- Программа лояльности и скидки для постоянных клиентов.
- Возможность заказа блюд заранее с учетом времени доставки.
- Оптимизация для планшетов и больших экранов.
- Оптимизация производительности и уменьшение потребления ресурсов.

## Лицензия

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

## Сделано с ❤️ и парой воков.