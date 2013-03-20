package org.sakaiproject.hierarchy.api;

import java.util.List;

import org.sakaiproject.exception.PermissionException;
import org.sakaiproject.hierarchy.api.model.PortalNode;
import org.sakaiproject.hierarchy.api.model.PortalNodeSite;

public interface PortalHierarchyService {

	String EVENT_NEW = "portal_hierarchy.new";
	String EVENT_DELETE = "portal_hierarchy.delete";
	String EVENT_MODIFY = "portal_hierarchy.modify";
	
	String SECURE_NEW = "portal.hierarchy.new";
	String SECURE_DELETE = "portal.hierarchy.delete";
	String SECURE_MODIFY = "portal.hierarchy.modify";

	String getCurrentPortalPath();

	/**
	 * Set the current PortalNode for this request.
	 * This only accepts PortalNodeSite as PortalNodeRedirect doesn't make
	 * any sense to be the current node.
	 */
	void setCurrentPortalNode(PortalNodeSite node);

	/**
	 * Get the current PortanNode for this request.
	 */
	PortalNodeSite getCurrentPortalNode();
	
	/**
	 * Get the node based on its nodePath.
	 * 
	 * @param nodePath The path of the node. If null or an empty string then get the node
	 * at the base of the service.
	 * @see #getRootNodes() 
	 * @return The found node or null if it couldn't be found.
	 */
	PortalNode getNode(String portalPath);

	PortalNode getNodeById(String id);
	
	PortalNode getDefaultNode(String siteId);
	
	/**
	 * Find all the nodes in the hierarchy with the selected site attached.
	 * @param siteId The ID of the site to search for.
	 * @return
	 */
	List<PortalNode> getNodesWithSite(String siteId);
	
	/**
	 * Get back the parent nodes for a Node. The supplied node is
	 * not contained in the list. The API enforces that PortalNodeSites can only be parents.
	 */
	List<PortalNodeSite> getNodesFromRoot(String nodeId);
	
	List<PortalNode> getNodeChildren(String nodeId);
	
	/**
	 * delete nodes also removes the properties
	 * 
	 * @param nodePath
	 * @throws IllegalStateException When there are still children of this node.
	 */
	void deleteNode(String id) throws PermissionException, IllegalStateException;

	PortalNode newSiteNode(String parentId, String childName, String siteId, String managementSiteId) throws PermissionException;
	
	PortalNode newRedirectNode(String parentId, String childName, String redirectUrl, String title, boolean appendPath) throws PermissionException;
	
	void renameNode(String id, String newPath) throws PermissionException;

	void moveNode(String id, String newParent) throws PermissionException;
	
	void changeSite(String id, String newSiteId) throws PermissionException;
	
	boolean canDeleteNode(String id);
	
	/**
	 * Can the current user add a new node to the node?
	 * @param parentId The nodeId of the parent.
	 * @return <code>true</code> if the node can be added.
	 */
	boolean canNewNode(String parentId);
	
	/**
	 * Can the current user rename the current node.
	 * @param id
	 * @return
	 */
	boolean canRenameNode(String id);
	
	/**
	 * Can the current user move the node to another location?
	 * @param id
	 * @return
	 */
	boolean canMoveNode(String id);
	
	boolean canChangeSite(String id);
	
	
}
