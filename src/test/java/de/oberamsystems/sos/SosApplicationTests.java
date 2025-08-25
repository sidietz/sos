package de.oberamsystems.sos;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import de.oberamsystems.sos.model.MyService;
import de.oberamsystems.sos.model.MyServiceRepository;
import de.oberamsystems.sos.model.NotRunner;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class SosApplicationTests {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MyServiceRepository repo;

	
	@Test
	public void testEqual() {
	    // given
	    MyService s = new MyService("sshd");
	    NotRunner nr = new NotRunner(s, LocalDateTime.now(), null);
	    entityManager.persist(s);
	    entityManager.flush();

	    List<MyService> found = repo.findByName(s.getName());

	    assertThat(found.get(0).getName()).isEqualTo(s.getName());
	}

	@Test
	public void testEqual2() {
	    MyService s = new MyService("sshd");
	    NotRunner nr = new NotRunner(s, LocalDateTime.now(), null);
	    NotRunner nr2 = new NotRunner(s, LocalDateTime.now(), null);
	    entityManager.persist(s);
	    entityManager.flush();

	    assertEquals(nr, nr2);
	}
	
	@Test
	public void testEqual5() {
		MyService s = new MyService("sshd");
		MyService s2 = new MyService("sshd");
		entityManager.persist(s);
		entityManager.persist(s2);
	    entityManager.flush();

	    assertNotEquals(s, s2);
	    assertEquals(s, s);
	}
	
	@Test
	public void testEqual3() {
	    MyService s = new MyService("sshd");
	    NotRunner nr = new NotRunner(s, LocalDateTime.now(), null);
	    List<NotRunner> oldNr = new ArrayList<NotRunner>();
	    oldNr.add(nr);
	    oldNr.add(new NotRunner(new MyService("samba"), LocalDateTime.now(), null));
	    
	    List<NotRunner> newNr = MasterScheduler.wentRunning(oldNr, new ArrayList<NotRunner>());

	    assertEquals(newNr.size(), (2));
	}
	
	@Test
	public void testEqual4() {
	    MyService s = new MyService("sshd");
	    NotRunner nr = new NotRunner(s, LocalDateTime.now(), null);
	    List<NotRunner> oldNr = new ArrayList<NotRunner>();
	    oldNr.add(nr);
	    
	    List<NotRunner> newNr = MasterScheduler.wentNotRunning(new ArrayList<NotRunner>(), oldNr);

	    assertThat(newNr.isEmpty());
	}

}
