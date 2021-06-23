from django.contrib.auth.models import AbstractBaseUser, BaseUserManager
from django.db import models


class UserManager(BaseUserManager):
    def create_user(self, username, user_name, user_stuid, password):
        user = self.model(username=username, user_name=user_name, user_stuid=user_stuid)
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, username, password, user_name, user_stuid):
        user = self.create_user(
            username=username,
            password=password,
            user_name=user_name,
            user_stuid=user_stuid,
        )
        user.is_superuser = True
        user.save(using=self._db)
        return user


class User(AbstractBaseUser):
    username = models.CharField(unique=True, max_length=16)
    password = models.CharField(max_length=32)
    user_name = models.CharField(max_length=16)
    user_stuid = models.CharField(max_length=9)

    objects = UserManager()

    USERNAME_FIELD = "username"
    REQUIRED_FIELDS = ["password", "user_name", "user_stuid"]

    def __str__(self) -> str:
        return self.username

    @property
    def is_staff(self):
        return self.is_staff
