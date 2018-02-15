package mz.ciuem.uclinica.test.dao;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import mz.ciuem.uclinica.conf.Configuracao;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Configuracao.class)
@SpringBootTest
public abstract class GenericDaoTest {
	


}
