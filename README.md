# Pocus

[![Android](https://img.shields.io/static/v1?style=flat-square&label=&message=Android&labelColor=212121&color=3DDC84&logoColor=3DDC84&logo=android)](https://developer.android.com/?hl=ko)
[![Django](https://img.shields.io/static/v1?style=flat-square&label=&message=Django&labelColor=e0e0e0&color=092E20&logoColor=092E20&logo=django)](https://www.djangoproject.com/)
[![MariaDB](https://img.shields.io/static/v1?style=flat-square&label=&message=MariaDB&labelColor=e0e0e0&color=003545&logoColor=003545&logo=mariadb)](https://mariadb.org/)
[![Docker](https://img.shields.io/static/v1?style=flat-square&label=&message=Docker&labelColor=212121&color=2496ED&logoColor=2496ED&logo=docker)](https://www.docker.com/)

소프트웨어 시스템 설계 실험 팀프로젝트

**문자 골라보기**

코로나가 유행하는 요즘, 안전재난 문자가 쏟아지는 현상에 불편함을 겪는 사람들이 생겼다.
알람을 꺼놓는 사람들이 늘어가면서 안전재난 메세지를 확인 하는 사람들이 감소하고 있다.
동선이 겹친 사람들에게는 긴급하고 중요한 정보전달을 도와주는 기능이 제 역할을 하지 못하게 되어버렸다.
이를 예방하기 위해서 위젯으로 안전재난 문자를 볼 수 있는 어플을 개발하게 되었다.

여기에 더불어 추가기능으로 특정한 키워드를 포함하는 문자를 골라볼 수 있는 기능을 추가했다.

```
.
+-- backend/
|   +-- ...
+-- frontend/
|   +-- ...
+-- ...
```

<br/>

# Features

- (위젯) 안전재난문자 리스트로 보여주기.

- (앱 내) 안전재난문자 종류별로 달력에서 확인하기.

- (앱 내) 키워드에 해당하는 문자를 사용자 지정 카테고리로 분류하기.

- (앱 내) 문자가 오면 카테고리/키워드로 분류해 보여주기.

<br/>

# Prerequisite

- Django v3.2.4
- Python v3.8.10
- Django REST framework v3.12.4
- Android Studio v11.0+
- docker v3
