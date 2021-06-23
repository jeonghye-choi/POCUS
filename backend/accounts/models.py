from django.contrib.auth.models import AbstractBaseUser, BaseUserManager
from django.db import models


class UserManager(BaseUserManager):
    def create_user(self, user_id, user_name, user_stuid, user_pw):
        user = self.model(user_id=user_id, user_name=user_name, user_stuid=user_stuid)
        user.set_password(user_pw)
        user.save(using=self._db)
        return user

    def create_superuser(self, user_id, user_pw, user_name, user_stuid):
        user = self.create_user(
            user_id=user_id, user_pw=user_pw, user_name=user_name, user_stuid=user_stuid
        )
        user.is_superuser = True
        user.save(using=self._db)
        return user


class User(AbstractBaseUser):
    user_id = models.CharField(unique=True, max_length=16)
    user_pw = models.CharField(max_length=32)
    user_name = models.CharField(max_length=16)
    user_stuid = models.CharField(max_length=9)

    objects = UserManager()

    USERNAME_FIELD = "user_id"
    REQUIRED_FIELDS = ["user_pw", "user_name", "user_stuid"]

    def __str__(self) -> str:
        return self.user_id

    @property
    def is_staff(self):
        return self.is_staff
