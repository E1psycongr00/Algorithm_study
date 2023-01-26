# 뒤에 있는 큰 수 찾기

[문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/154539?language=java)

## 문제 요구 사항

### 문제

- 뒷 큰수
  - 자신보다 뒤에 있는 숫자 중에서 자신보다 크면서 가장 가까이 있는 수
  -

### 제한 사항

- numbers 길이 4 ~ 100만

## 문제 해결 전략

- 모노토닉 스택을 활용한다.
  - index를 넣는다.
  - numbers를 참조하는 index의 value 값 보다 큰 값이 들어가면 해당 index를 큰 값 valu로 업데이트 해준다.
  - 나머지 업데이트 못한 값들은 -1로 초기화해준다.

## 예제

[2,3,3,5] => [3, 5, 5, -1]
