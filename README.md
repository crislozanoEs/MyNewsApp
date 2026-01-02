My News application.

Application that shows a list of news based on a open API (thenewsapi), and the details of the news.
It has two screens:
1- A list of news. It shows the title, image, and summary.
2- The detials of a piece of news. It shows more details of the selected news.

It uses Clean Architecture, Jetpack Compose, and MVI. It also uses other dependencies like Flows, Coil, Retrofit, and Navigation Component.

There is a limit for the free open API of 100 request per day. After the limit is reached, it will return an error.
