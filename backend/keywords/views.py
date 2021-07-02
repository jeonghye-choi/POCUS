from rest_framework import serializers
from rest_framework import status
from rest_framework.response import Response
from rest_framework.decorators import api_view
from rest_framework.serializers import SerializerMetaclass
from django.shortcuts import render, get_object_or_404

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

@api_view(['GET','DELETE'])
def keyword_delete(request, keyword_pk):
    keyword = get_object_or_404(Keyword, pk=keyword_pk)
    '''
    GET /keywords/{keyword_pk}
    DELETE /keywords/{keyword_pk}
    '''
    if request.method == 'GET':
        serializer = KeywordSerializer(keyword)  #JSON으로 만들기
        return Response(serializer.data)
    
    if request.method == 'DELETE':
        keyword.delete()
        data = {'id' : keyword_pk}
        return Response(data, status=status.HTTP_204_NO_CONTENT)