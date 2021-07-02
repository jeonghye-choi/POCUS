from django.urls import path
from rest_framework import routers
from . import views
app_name = 'keywords'

urlpatterns = [
    path('', views.keyword_create),
    path('<int:keyword_pk>', views.keyword_delete)
]