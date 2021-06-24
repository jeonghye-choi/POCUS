from django.db import models

class Keyword(models.Model):
    user_id = models.IntegerField(unique=True)
    keyword = models.CharField(max_length=100)
    category = models.CharField(max_length=100)

    REQUIRED_FIELDS = ["user_id", "keyword", "category"]

    def __str__(self) -> str:
        return self.keyword