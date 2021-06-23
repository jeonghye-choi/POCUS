from django.urls import path
from rest_framework import routers
from . import views
app_name = 'publicdata'

urlpatterns = [
    path("covid/", views.covid)
]