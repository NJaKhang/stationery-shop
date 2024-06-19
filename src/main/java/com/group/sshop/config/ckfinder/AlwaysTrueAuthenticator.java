package com.group.sshop.config.ckfinder;

import com.cksource.ckfinder.authentication.Authenticator;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class AlwaysTrueAuthenticator  implements Authenticator {

    @Override
    public boolean authenticate() {

        return true;
    }
}