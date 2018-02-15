package mz.ciuem.uclinica.auth.service.impl;

import org.springframework.stereotype.Service;

import mz.ciuem.uclinica.auth.Permission;
import mz.ciuem.uclinica.auth.service.PermissionService;
import mz.ciuem.uclinica.service.impl.GenericServiceImpl;

@Service("permissionService")
public class PermissionServiceImpl extends GenericServiceImpl<Permission> implements PermissionService {

}
