package br.com.uol.pagseguro.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a PagSeguro authorization
 */
public class Authorization {

    /** Authorization code **/
    private String code;

    /** Date of the authorization creation */
    private Date date;

    /**
     * A reference to associate the PagSeguro authorization to a authorization
     * in your system.
     */
    private String reference;

    /** Permission list of this authorization **/
    private final List<Permission> permissions;

    public Authorization() {

        permissions = new ArrayList<Permission>();

    }

    /**
     * Add Permissions
     */
    public void addPermission(final Permission permission) {
        if (permission != null) {
            permissions.add(permission);
        }
    }

    /**
     * Get authorization code
     */
    public String getCode() {
        return code;
    }

    /**
     * Get creation date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the list of permissions
     */
    public List<Permission> getPermissions() {
        return permissions;
    }

    /**
     * Get Reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * Set authorization code
     */
    public void setCode(final String code) {
        this.code = code;
    }

    /**
     * Set creation date
     */
    public void setDate(final Date date) {
        this.date = date;
    }

    /**
     * Set Reference
     */
    public void setReference(final String reference) {
        this.reference = reference;
    }

}
