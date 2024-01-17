package com.jpa.querydsl;

import com.jpa.querydsl.entity.Hello;
import com.jpa.querydsl.entity.QHello;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@PersistenceContext
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		// 1. JpaQueryFactory 생성 및 EntityManager 주입
		// querydsl 최신 버전에서는 JpaQueryFactory 사용을 권장 한다.
		JPAQueryFactory query = new JPAQueryFactory(em);
		QHello qHello = QHello.hello;

		// 2. querydsl 사용
		Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		// 3. 검증
		/* 기존 entity 와 Qtype 으로 조회한 entity 가 동일 한지 */
		assertThat(result).isEqualTo(hello);
		/* 각 결과 값의 Id 값이 동일 한지 */
		assertThat(result.getId()).isEqualTo(hello.getId());
	}
}
