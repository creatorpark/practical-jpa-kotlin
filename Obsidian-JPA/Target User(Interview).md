JPA를 주저하는 이유
Q. 샘플로만 보면 이게 실전에서 MyBatis 쓰는 것보다 더 좋은지 모르겠다.
JPQL vs QueryDSL
get/set vs Mapper
Controller 
-> 이 것은 샘플이 jpql이나 Mapper를 안쓰고 get/set을 이용해 Entity를 받기 때문이다.
-> 다른 자동화 컨셉이 적용되지 않아서 쓰기 힘들다.

Q. 샘플소스와 실전소스 사이의 갭차이가 많이 존재한다.
Entity State가 어떤지 자세하게 설명되거나 실전에서 어떤 영향이 있는지 설명된게 부족하다.


중요한 개념

1. 인지 과부화
Layered Architecture
Hexagonal Architecture

2. 프로그램 복잡도 상수화
-> 비지니스에 집중
-> 단순 노가다 상수화

3. Best Practice
- Transactional Listener
