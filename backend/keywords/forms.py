from django import forms
from django.forms import fields
from .models import Keyword

class KeywordForm(forms.ModelForm):
    class Meta:
        model = Keyword
        fields = ('keyword', 'category',)