# 테이블 해시 함수

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/147354)

## 문제 요구 사항

### 문제

- 열 -> 컬럼, 행 -> 튜플
- 해시 함수는 col, row_begin, row_end로 입력 받음
- 테이블의 튜플을 col번쨰 컬럼 값을 기준으로 오름차순 정렬, 만약 동일한 경우 첫번째 컬럼의 값을 기준으로 내림차순 정렬
- 정렬된 데이터에서의 S_i => sum(i번째 컬럼) % i
- row_bigin <= i <= row_end인 모든 S_i를 누적해서 bitwise xor한 값을 해시 값으로 사용

ex ) 78 -> 83

### 제한 사항

- data의 길이 1 ~ 2500
- data 원소의 길이 1 ~ 500
- data[i][j] 값 1 ~ 100만 이때 data [i][j] 는 i+1번쨰 튜플의 j+1번쨰 컬럼의 값
- col은 1 ~ data 원소 길이
- 1 <= row_begin <= row_end <= data 길이

## 문제 해결 전략

1. 테이블을 col asc, 첫뻔재 desc로 정렬한다.
2. 행을 기준으로 튜플 값의 모든 핪을 mod로 더함.
3. 해쉬값 구하기

입출력 예#1

data col row_begin row_end result
[[2,2,6],[1,5,10],[4,2,9],[3,8,3]] 2 2 3 4

4 2 9
2 2 6
1 5 10
3 8 3
