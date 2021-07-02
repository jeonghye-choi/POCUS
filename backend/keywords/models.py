from django.db import models
from accounts.models import User

class Keyword(models.Model):
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    keyword = models.CharField(max_length=100)
    category = models.CharField(max_length=100)

    REQUIRED_FIELDS = ["user_id", "keyword", "category"]

    def __str__(self) -> str:
        return self.keyword
