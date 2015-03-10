package br.com.uol.pagseguro.domain;

import java.util.Date;
import br.com.uol.pagseguro.enums.PermissionStatus;
import br.com.uol.pagseguro.enums.PermissionType;

public class Permission {

    /** Permission of an authorization **/
    private PermissionType permission;

    /** Status of this permission **/
    private PermissionStatus status;

    /** Last update date of this permission **/
    private Date lastUpdate;

    /**
     * Get last update date
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Get Permission
     */
    public PermissionType getPermission() {
        return permission;
    }

    /**
     * Get Status
     */
    public PermissionStatus getStatus() {
        return status;
    }

    /**
     * Set last update date
     */
    public void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * Set Permission
     */
    public void setPermission(final PermissionType permission) {
        this.permission = permission;
    }

    /**
     * Set Status
     */
    public void setStatus(final PermissionStatus status) {
        this.status = status;
    }

}
