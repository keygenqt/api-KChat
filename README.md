KChat (server)
===================
_(in progress)_

![picture](data/preview.png)

Chat completely built on kotlin Ktor [client](https://github.com/keygenqt/android-KChat) and [server](https://github.com/keygenqt/api-KChat).

### Architecture
* Framework - [Ktor](https://ktor.io/)
* DI - [Koin](https://insert-koin.io/)
* Protocol - [WebSocket (chat)](https://en.wikipedia.org/wiki/WebSocket), [HTTP (REST API)](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)
* Data Base - [MySQL](https://www.mysql.com/)
* Migration - [Flyway](https://flywaydb.org/)
* Template -  [kotlinx.html](https://github.com/Kotlin/kotlinx.html)
* Authorization - [Basic](https://ktor.io/docs/basic.html) + [Firebase Admin](https://firebase.google.com/docs/admin/setup)

![picture](data/f-preview.png)

# License

```
Copyright 2021 Vitaliy Zarubin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```