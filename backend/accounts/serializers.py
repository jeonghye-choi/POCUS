from rest_framework import serializers


class UserSerializer(serializers.Serializer):
    user_id = serializers.CharField()
    user_pw = serializers.CharField()
    user_name = serializers.CharField()
    user_stuid = serializers.CharField()
