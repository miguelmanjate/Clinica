package mz.ciuem.uclinica.entity.consulta;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import mz.ciuem.uclinica.auth.Permission;

public class PermissionsFormatter implements Formatter<Permission> {
	
	
	@Override
	public String print(Permission permission, Locale locale) {
		return permission.getId().toString();
	}

	@Override
	public Permission parse(String id, Locale locale) throws ParseException {
		Permission permission = new Permission();
	permission.setId(Long.valueOf(id));
		return permission;
	}

}
