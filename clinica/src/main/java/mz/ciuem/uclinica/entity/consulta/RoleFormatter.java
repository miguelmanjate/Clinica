package mz.ciuem.uclinica.entity.consulta;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import mz.ciuem.uclinica.auth.Role;

public class RoleFormatter implements Formatter<Role> {

	@Override
	public String print(Role role, Locale locale) {
		return role.getId().toString();
	}

	@Override
	public Role parse(String id, Locale locale) throws ParseException {
		Role role = new Role();
		role.setId(Long.valueOf(id));
		return role;
	}

}
