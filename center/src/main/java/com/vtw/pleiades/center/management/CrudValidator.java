package com.vtw.pleiades.center.management;

import com.vtw.pleiades.center.common.web.validation.ValidationResult;

public interface CrudValidator<T, ID> {

	ValidationResult validate(T server);

	ValidationResult validate(ID id, T server);
}
