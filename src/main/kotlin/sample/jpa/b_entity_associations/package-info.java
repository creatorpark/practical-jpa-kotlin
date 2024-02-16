package sample.jpa.b_entity_associations;

/**
 * Entity Relation 은
 * Object의 협력관계를 지원하기 위해
 * TABLE상에 저장되어 있는 데이터를 Object로 매핑하는 기능을 제공하는 것이다.
 * <p>
 * Object에서는 관계를 참조(Reference)로 구현하고
 * RDB에서는 관계 FK로 구현한다.
 * <p>
 * 단방향, 양방향
 * TABLE은 방향이라는 개념이 없지만, Object에서는 데이터의 조회(객체 그래프 탐색)을 위해 방향성을 부여한다.
 * 단방향은 두 Object가 A TO B 하나의 방향으로만 조회 가능한 것이며, 참조가 하나다.
 * 양방향은 두 Object가 A TO B, B TO A 양방향으로 조회 가능한 것이다. 참조가 2개다. (단방향 X2)
 * <p>
 * 양방향이어도 FK 관리(COMMAND)는 한 방향에서만 해야 하는데,
 * 이 FK 관리의 주체를 연관 관계의 주인(소유자:Owning)라고 한다.
 * Object(TABLE)의 참조(FK)가 저장 되는 곳이다.
 * 대부분 FK가 있는 곳에 연관 관계의 주인이다.
 * <p>
 * 이 룰을 구현하기 위해 JPA에서는 다음 2개의 속성을 지원한다.
 * FK 관리 주체 (Owning Side)
 * JoinColumn = name =FK, ref-col = Entity PK를 적는다.
 * <p>
 * FK 읽기만 가능 (Non-Owning Side)
 * mappedBy = 주인이 아니면 이 속성을 적는다.
 * 읽기만 하겠다는 선언이다. 읽기 전용인 곳에만 값을 세팅하면 FK는 null이 된다.
 * <p>
 * 보통은 연관관계의 주인 Entity에서 2군데 모두 값을 모두 넣는 관리 메소드를 구현 한다.
 * <p>
 * 세팅을 안할 경우
 * 1. em find 해서 다시 가져오면 메모리에서만 가져오니까 한군데는 연결이 안되어 있다.
 */



