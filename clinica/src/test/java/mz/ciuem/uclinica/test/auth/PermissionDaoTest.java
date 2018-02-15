package mz.ciuem.uclinica.test.auth;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import mz.ciuem.uclinica.auth.Authorization;
import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.auth.dao.PermissionDao;
import mz.ciuem.uclinica.test.GenericTest;

@Transactional
public class PermissionDaoTest extends GenericTest {

	@Autowired
	private PermissionDao permissionDao;

	@Test
	public void createPermissionTest() {

		long totalPermission = permissionDao.count();

		Permission permission = new Permission();
		permission.setAuthorization(Authorization.ROLE_ADMIN);
		permissionDao.create(permission);

		Assert.assertEquals(totalPermission + 1, permissionDao.count());

	}

	@Test
	public void actualicaoDaDesignacaoDaPermissaoTest() {

		Permission permission = new Permission();
		permission.setAuthorization(Authorization.ROLE_USER);
		permissionDao.create(permission);
		permission.setAuthorization(Authorization.ROLE_ADMIN);
		permissionDao.update(permission);

		Assert.assertEquals(permission.getAuthorization(), Authorization.ROLE_ADMIN);

	}

	@Test
	public void buscarPermissionPeloNome() {

		List<Permission> permissions = permissionDao.bucarPelaAutorizacao(Authorization.ROLE_ADMIN);

		
		int quantidadeActual = permissions == null ? 0 : permissions.size();
		Permission permission = new Permission();
		permission.setAuthorization(Authorization.ROLE_ADMIN);
		permissionDao.saveOrUpdate(permission);

		Assert.assertEquals(quantidadeActual + 1, permissionDao.bucarPelaAutorizacao(Authorization.ROLE_ADMIN).size());

	}

}
