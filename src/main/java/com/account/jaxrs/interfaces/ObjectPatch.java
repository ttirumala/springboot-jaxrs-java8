package com.account.jaxrs.interfaces;

import com.account.jaxrs.patch.ObjectPatchException;

public interface ObjectPatch {

    <T> T apply(T target) throws ObjectPatchException;

}