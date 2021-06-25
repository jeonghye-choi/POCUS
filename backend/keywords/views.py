from rest_framework import serializers
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework.serializers import SerializerMetaclass

from .models import Keyword
from .serializers import KeywordSerializer

@api_view(['GET', 'POST'])
def keyword_create(request):
    '''
    GET /keywords/
    '''
    if request.method == 'GET':
        # 1. 디비에 있는 모든 keywords를 가져온다.
        # 2. 전송가능한 형태(JSON)으로 바꿔준다.
        # 3. 마지막으로 Response 함수를 이용해 반환
        keywords = Keyword.objects.all()
        serializer = KeywordSerializer(keywords, many=True)
        
        return Response(data=serializer.data)

    '''
    POST /keywords/
    '''
    if request.method == 'POST':
        serializer = KeywordSerializer(data=request.data)
        if serializer.is_valid(raise_exception=True):
            serializer.save()
            return Response(data=serializer.data, status=status.HTTP_201_CREATED)