package com.tib.ts.mod.entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
@ToString
public class SemanticArtefact extends Resource {
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private Object semanticArtefactId;
	
	@JsonProperty("@type")
	private Object semanticArtefactType;
	
	@JsonProperty("useGuidelines")
	private List<Map<String, String>> useGuidelines;

	@JsonProperty("morePermissions")
	private List<Map<String, String>> morePermissions;

	@JsonProperty("downloadURL")
	private List<Map<String, String>> downloadURL;

	@JsonProperty("primaryTopic")
	private List<Map<String, String>> primaryTopic;

	@JsonProperty("abstract")
	private List<Map<String, String>> abstracts;

	@JsonProperty("accrualMethod")
	private List<Map<String, String>> accrualMethod;

	@JsonProperty("accrualPeriodicity")
	private List<Map<String, String>> accrualPeriodicity;

	@JsonProperty("accrualPolicy")
	private List<Map<String, String>> accrualPolicy;

	@JsonProperty("alternative")
	private List<Map<String, String>> alternative;

	@JsonProperty("audience")
	private List<Map<String, String>> audience;

	@JsonProperty("bibliographicCitation")
	private List<Map<String, String>> bibliographicCitation;

	@JsonProperty("contributor")
	private List<Map<String, String>> contributor;

	@JsonProperty("coverage")
	private List<Map<String, String>> coverage;

	@JsonProperty("hasFormat")
	private List<Map<String, String>> hasFormat;

	@JsonProperty("hasPart")
	private List<Map<String, String>> hasPart;

	@JsonProperty("hasVersion")
	private List<Map<String, String>> hasVersion;

	@JsonProperty("isFormatOf")
	private List<Map<String, String>> isFormatOf;

	@JsonProperty("isPartOf")
	private List<Map<String, String>> isPartOf;

	@JsonProperty("rightsHolder")
	private List<Map<String, String>> rightsHolder;

	@JsonProperty("source")
	private List<Map<String, String>> source;

	@JsonProperty("bug-database")
	private List<Map<String, String>> bugDatabase;

	@JsonProperty("mailing-list")
	private List<Map<String, String>> mailingList;

	@JsonProperty("repository")
	private List<Map<String, String>> repository;

	@JsonProperty("depiction")
	private List<Map<String, String>> depiction;

	@JsonProperty("fundedBy")
	private List<Map<String, String>> fundedBy;

	@JsonProperty("homepage")
	private List<Map<String, String>> homepage;

	@JsonProperty("logo")
	private List<Map<String, String>> logo;

	@JsonProperty("acronym")
	private String acronym;

	@JsonProperty("analytics")
	private List<Map<String, String>> analytics;

	@JsonProperty("comesFromTheSameDomain")
	private List<Map<String, String>> comesFromTheSameDomain;

	@JsonProperty("competencyQuestion")
	private List<Map<String, String>> competencyQuestion;

	@JsonProperty("curatedBy")
	private List<Map<String, String>> curatedBy;

	@JsonProperty("createdWith")
	private List<Map<String, String>> createdWith;

	@JsonProperty("designedForTask")
	private SemanticArtefactService designedForTask;

	@JsonProperty("endorsedBy")
	private List<Map<String, String>> endorsedBy;

	@JsonProperty("generalizes")
	private List<Map<String, String>> generalizes;

	@JsonProperty("group")
	private List<Map<String, String>> group;

	@JsonProperty("hasDisjunctionsWith")
	private List<Map<String, String>> hasDisjunctionsWith;

	@JsonProperty("hasDisparateModelling")
	private List<Map<String, String>> hasDisparateModelling;

	@JsonProperty("hasEquivalencesWith")
	private List<Map<String, String>> hasEquivalencesWith;

	@JsonProperty("hasEvaluation")
	private List<Map<String, String>> hasEvaluation;

	@JsonProperty("hasFormalityLevel")
	private List<Map<String, String>> hasFormalityLevel;

	@JsonProperty("knownUsage")
	private List<Map<String, String>> knownUsage;

	@JsonProperty("metrics")
	private List<Map<String, String>> metrics;

	@JsonProperty("numberOfAgents")
	private List<Map<String, String>> numberOfAgents;

	@JsonProperty("numberOfEnsorments")
	private List<Map<String, String>> numberOfEnsorments;

	@JsonProperty("numberOfEvaluations")
	private List<Map<String, String>> numberOfEvaluations;

	@JsonProperty("numberOfNotes")
	private List<Map<String, String>> numberOfNotes;

	@JsonProperty("numberOfUsers")
	private List<Map<String, String>> numberOfUsers;

	@JsonProperty("numberOfUsingProjects")
	private List<Map<String, String>> numberOfUsingProjects;

	@JsonProperty("reliesOn")
	private List<Map<String, String>> reliesOn;

	@JsonProperty("semanticArtefactRelation")
	private List<Map<String, String>> semanticArtefactRelation;

	@JsonProperty("similar")
	private List<Map<String, String>> similar;

	@JsonProperty("specializes")
	private List<Map<String, String>> specializes;

	@JsonProperty("status")
	private List<Map<String, String>> status;

	@JsonProperty("toDoList")
	private List<Map<String, String>> toDoList;

	@JsonProperty("URI")
	private List<Map<String, String>> uri;

	@JsonProperty("usedBy")
	private List<Map<String, String>> usedBy;

	@JsonProperty("usedInProject")
	private List<Map<String, String>> usedInProject;

	@JsonProperty("deprecated")
	private List<Map<String, String>> deprecated;

	@JsonProperty("priorVersion")
	private List<Map<String, String>> priorVersion;

	@JsonProperty("versionInfo")
	private List<Map<String, String>> versionInfo;

	@JsonProperty("versionIRI")
	private List<Map<String, String>> versionIRI;

	@JsonProperty("wasGeneratedBy")
	private List<Map<String, String>> wasGeneratedBy;

	@JsonProperty("wasInvalidatedBy")
	private List<Map<String, String>> wasInvalidatedBy;

	@JsonProperty("comment")
	private List<Map<String, String>> comment;

	@JsonProperty("associatedMedia")
	private List<Map<String, String>> associatedMedia;

	@JsonProperty("award")
	private List<Map<String, String>> award;

	@JsonProperty("schemaComment")
	private List<Map<String, String>> schemaComment;

	@JsonProperty("funding")
	private List<Map<String, String>> funding;

	@JsonProperty("includedInDataCatalog")
	private List<Map<String, String>> includedInDataCatalog;

	@JsonProperty("translator")
	private List<Map<String, String>> translator;

	@JsonProperty("hiddenLabel")
	private List<Map<String, String>> hiddenLabel;

	@JsonProperty("changes")
	private List<Map<String, String>> changes;

	@JsonProperty("example")
	private List<Map<String, String>> example;

	@JsonProperty("preferredNamespacePrefix")
	private List<Map<String, String>> preferredNamespacePrefix;

	@JsonProperty("preferredNamespaceUri")
	private List<Map<String, String>> preferredNamespaceUri;

	@JsonProperty("openSearchDescription")
	private List<Map<String, String>> openSearchDescription;

	@JsonProperty("classPartition")
	private List<Map<String, String>> classPartition;

	@JsonProperty("exampleResource")
	private List<Map<String, String>> exampleResource;

	@JsonProperty("rootResource")
	private List<Map<String, String>> rootResource;

	@JsonProperty("propertyPartition")
	private List<Map<String, String>> propertyPartition;

	@JsonProperty("uriLookupEndpoint")
	private List<Map<String, String>> uriLookupEndpoint;

	@JsonProperty("uriRegexPattern")
	private List<Map<String, String>> uriRegexPattern;

	@JsonGetter("@id")
    public String getIdAsString() {
        return semanticArtefactId != null ? semanticArtefactId.toString() : null;
    }
	
	@JsonGetter("@type")
    public String getTypeAsString() {
        return semanticArtefactType != null ? semanticArtefactType.toString() : "mod:semanticArtefact";
    }
}
