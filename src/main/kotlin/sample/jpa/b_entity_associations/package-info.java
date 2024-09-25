package sample.jpa.b_entity_associations;

/*
 * Entity Relation 은
 * Object의 협력관계를 지원하기 위해
 * TABLE상에 저장되어 있는 데이터를 Object로 매핑하는 기능을 제공하는 것이다.
 *
 * Object에서는 관계를 참조(Reference)로 구현하고
 * RDB에서는 관계를 FK로 구현한다.
 *
 * 단방향, 양방향
 * RDB에서는 방향이라는 개념이 없지만,
 * Object에서는 데이터 조회(객체 그래프 탐색)을 위해 방향성을 부여한다.
 *
 * 단방향은 두 Object가 A TO B 한 방향으로만 조회한다. 참조가 하나다.
 * 양방향은 두 Object가 A TO B, B TO A 두 방향으로 조회한다. 참조가 2개다.
 *
 * 양방향이어도 FK 관리(COMMAND Query)는 한 방향에서만 해야 하는데,
 * 이 FK를 소유한 쪽을 연관 관계의 주인(소유자:Owning)이라고 한다.
 * Object(TABLE)의 참조(FK)가 저장 되는 곳이다.
 *
 * 이 룰을 구현하기 위해 JPA에서는 다음 2개의 주요 개념이 있다.
 * 1. 소유자(Owning)는 @JoinColumn( name = FK, ref-col = Entity PK)를 적는다.
 * 2. 비소유자(Non-Owning)는 mappedBy 속성을 사용한다.
 * 매핑이 저쪽에 있으니까 저걸 참조해. 라는 뜻인듯
 *
 * 보통은 Owner Entity에서 관리 메소드를 만들어 Non-Owning까지 2군데 모두 값을 넣는다.
 * 1. 그렇지 않고, Owner에서 Entity 주인쪽에서 1군데만 적으면 저장하고 나서는 문제가 없지만,
 * 저장하기 전에는 non-Owner를 호출하면 연결이 안되어 있어서 문제가 되서 개념상 맞지 않는다.
 *
 * 2. mappedBy(Non-Owning)에서만 값을 설정하는 경우 JPA는 저장시 FK를 저장하지 않는다.
 * (읽기 전용이기 때문에)
 */
