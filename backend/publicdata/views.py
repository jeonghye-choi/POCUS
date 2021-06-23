from django.shortcuts import render
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework import authentication, permissions
from rest_framework.views import APIView
from rest_framework.decorators import api_view
import requests
import env
import json

@api_view(["GET"])
def covid(request):

    serviceUrl = 'http://apis.data.go.kr/1741000/DisasterMsg2/getDisasterMsgList?ServiceKey=' + env.API_KEY

    pageNo = '1'
    numOfRows = '10'
    flag = 'Y'
    data_type = 'json'

    parameters = {
        # "ServiceKey": serviceKey,
        "pageNo": pageNo,
        "numOfRows": numOfRows,
        "flag": flag,
        "type": data_type
    }

    # response = requests.get(serviceUrl, params=parameters)
    

    covidList = []

    if  True or response.status_code == 200:
        # data = json.loads(response.text)
        # DisasterMsg = data['DisasterMsg']
        # iterator = DisasterMsg[1]['row']

        # print(iterator)
        # iter = data.iter(tag="row")
        # print("iter", iter)

        iterator = [{'create_date': '2021/06/23 18:33:23', 'location_id': '15', 'location_name': '강원도 춘천시', 'md101_sn': '109833', 'msg': '[춘천시청] 진단검사 행정명령 / 6.24(목)부터 코로나19 유증상자는 보건소, 봄내체육관(드라이브스루) 선별진료소(09시~17시)에서 진단검사 바랍니다.', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:26:31', 'location_id': '46', 'location_name': '경기도 의정부시', 'md101_sn': '109832', 'msg': '[의정부시청] 6월 23일 18시 기준, 일일 확진자 4명 발생(1547~1550번) 홈페이지 및 블로그 https://c11.kr/joao 참고 바랍니다.', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:22:59', 'location_id': '32', 'location_name': '경기도 부천시', 'md101_sn': '109831', 'msg': '[부천시청] 6.14(월)~6.22(화) 가림독서실(부흥로 398, 5층/심곡동) 방문자는 증상유무에 관계없이 선별진료소에서 코로나19 검사를 받으시기 바랍니다.', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:10:46', 'location_id': '39', 'location_name': '경기도 양주시', 'md101_sn': '109830', 'msg': '[양주시청] 6월 23일 18시 기준, 일일 확진자 1명 발생 (타시군 144번). 관련정보는 홈페이지 및 블로그 참고바랍니다. http://c11.kr/o1wn', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:06:14', 'location_id': '30', 'location_name': '경기도 남양주시', 'md101_sn': '109829', 'msg': '[남양주시청] 6.23.(수) 남양주시 9명(타지역 주민 2명 포함) 코로나19 확진. 방역사항은 홈페이지 참고 바랍니다. c11.kr/nyjcn', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:02:51', 'location_id': '52', 'location_name': '경기도 화성시', 'md101_sn': '109828', 'msg': '[화성시청] 6월 23일 18시 현재, 확진자 7명(화성 1589~1595번) 발생하였습니다. 상세내용은 hscity.go.kr/crn.do 참조', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:02:05', 'location_id': '37', 'location_name': '경기도 안성시', 'md101_sn': '109827', 'msg': '[안성시청]6.23. 18시 기준 코로나19 확진자 2명(확진자 접촉자2)발생\n역학조사, 방역조치 완료\nanseong.go.kr/corona_index.jsp', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:00:59', 'location_id': '237', 'location_name': '충청남도 홍성군', 'md101_sn': '109826', 'msg': '[홍성군청]6월23일(수) 18시 기준 확진자 1명(홍성104번) 발생. 타지역 거주자로 홍성보건소 선별진료소 방문검사. 의심증상 발현시 보건소 검사바랍니다', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:00:06', 'location_id': '113', 'location_name': '대전광역시 전체', 'md101_sn': '109825', 'msg': '[대전광역시] 오늘 18시 기준, 14명 추가 발생.(동2, 중4, 서3, 유성4, 대덕1). 자세한 사항은 홈페이지 참조.\n(참고) 어제 하루 총 58명 발생.', 'send_platform': 'cbs'}, {'create_date': '2021/06/23 18:00:03', 'location_id': '32', 'location_name': '경기도 부천시', 'md101_sn': '109824', 'msg': '[부천시청] 6월 23일(수) 18:00기준 코로나19확진자 14명 발생, 세부내용은 블로그 참조하시기 바랍니다\nblog.naver.com/bucheon-city', 'send_platform': 'cbs'}]

        for element in iterator:
            location_id = element['location_id'] #수신지역 코드
            location_name = element['location_name'] #수신지역 이름
            md101_sn = element['md101_sn']  #일련번호
            msg = element['msg']  #내용
            send_platform = element['send_platform']  #발신처

            data = {
                "location_id": location_id,
                "location_name": location_name,
                "md101_sn": md101_sn,
                "msg": msg,
                "send_platform": send_platform
            }

            print('data', data, '\n')
            covidList.append(data)
    
    context = {'covidList': covidList}
    print(covidList)

    return Response(data)