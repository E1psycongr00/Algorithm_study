# 전화번호 목록

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/42577?language=java)

## 문제 요구 사항

### 문제

전화번호부에 적힌 전화번호 중, 한 번호가 다른 벊의 접두어인 경우가 있는지 확인
구조대: 119
박준영: 97 674 223
지영석: 11 9552 4421

### 제한 사항

- phone book이 1이상 100만개 이하

## 문제 해결 전략

- 문자열 정렬 후 인접 배열을 비교한다.
  - 문자열 정렬은 앞부분 부터 아스키 코드값을 비교하여 정렬한다. 만약 같다면 다음 아스키 코드 값을 비교하는 형식이다.
  - 만약 접두사가 같은 문자열이 존재한다면 정렬시 필연적으로 붙어있을 수 밖에 없다.

입출력 예#1

phone_book return

["119", "97674223", "1195524421"] false

["123","456","789"] true

["12","123","1235","567","88"] false
