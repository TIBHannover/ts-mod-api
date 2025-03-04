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
	
	@JsonProperty("cc:useGuidelines")
	private List<Map<String, String>> useGuidelines;

	@JsonProperty("cc:morePermissions")
	private List<Map<String, String>> morePermissions;

	@JsonProperty("dcat:downloadURL")
	private List<Map<String, String>> downloadURL;

	@JsonProperty("foaf:primaryTopic")
	private List<Map<String, String>> primaryTopic;

	@JsonProperty("dcterms:abstract")
	private List<Map<String, String>> abstracts;

	@JsonProperty("dcterms:accrualMethod")
	private List<Map<String, String>> accrualMethod;

	@JsonProperty("dcterms:accrualPeriodicity")
	private List<Map<String, String>> accrualPeriodicity;

	@JsonProperty("dcterms:accrualPolicy")
	private List<Map<String, String>> accrualPolicy;

	@JsonProperty("dcterms:alternative")
	private List<Map<String, String>> alternative;

	@JsonProperty("dcterms:audience")
	private List<Map<String, String>> audience;

	@JsonProperty("dcterms:bibliographicCitation")
	private List<Map<String, String>> bibliographicCitation;

	@JsonProperty("dcterms:contributor")
	private List<Map<String, String>> contributor;

	@JsonProperty("dcterms:coverage")
	private List<Map<String, String>> coverage;

	@JsonProperty("dcterms:hasFormat")
	private List<Map<String, String>> hasFormat;

	@JsonProperty("dcterms:hasPart")
	private List<Map<String, String>> hasPart;

	@JsonProperty("dct:hasVersion")
	private List<Map<String, String>> hasVersion;

	@JsonProperty("dcterms:isFormatOf")
	private List<Map<String, String>> isFormatOf;

	@JsonProperty("dct:isPartOf")
	private List<Map<String, String>> isPartOf;

	@JsonProperty("dcterms:rightsHolder")
	private List<Map<String, String>> rightsHolder;

	@JsonProperty("dcterms:source")
	private List<Map<String, String>> source;

	@JsonProperty("doap:bug-database")
	private List<Map<String, String>> bugDatabase;

	@JsonProperty("doap:mailing-list")
	private List<Map<String, String>> mailingList;

	@JsonProperty("doap:repository")
	private List<Map<String, String>> repository;

	@JsonProperty("foaf:depiction")
	private List<Map<String, String>> depiction;

	@JsonProperty("foaf:fundedBy")
	private List<Map<String, String>> fundedBy;

	@JsonProperty("foaf:homepage")
	private List<Map<String, String>> homepage;

	@JsonProperty("foaf:logo")
	private List<Map<String, String>> logo;

	@JsonProperty("mod:acronym")
	private List<Map<String, String>> acronym;

	@JsonProperty("mod:analytics")
	private List<Map<String, String>> analytics;

	@JsonProperty("mod:comesFromTheSameDomain")
	private List<Map<String, String>> comesFromTheSameDomain;

	@JsonProperty("mod:competencyQuestion")
	private List<Map<String, String>> competencyQuestion;

	@JsonProperty("mod:curatedBy")
	private List<Map<String, String>> curatedBy;

	@JsonProperty("mod:createdWith")
	private List<Map<String, String>> createdWith;

	@JsonProperty("mod:designedForTask")
	private SemanticArtefactService designedForTask;

	@JsonProperty("mod:endorsedBy")
	private List<Map<String, String>> endorsedBy;

	@JsonProperty("mod:generalizes")
	private List<Map<String, String>> generalizes;

	@JsonProperty("mod:group")
	private List<Map<String, String>> group;

	@JsonProperty("mod:hasDisjunctionsWith")
	private List<Map<String, String>> hasDisjunctionsWith;

	@JsonProperty("mod:hasDisparateModelling")
	private List<Map<String, String>> hasDisparateModelling;

	@JsonProperty("mod:hasEquivalencesWith")
	private List<Map<String, String>> hasEquivalencesWith;

	@JsonProperty("mod:hasEvaluation")
	private List<Map<String, String>> hasEvaluation;

	@JsonProperty("mod:hasFormalityLevel")
	private List<Map<String, String>> hasFormalityLevel;

	@JsonProperty("mod:knownUsage")
	private List<Map<String, String>> knownUsage;

	@JsonProperty("mod:metrics")
	private List<Map<String, String>> metrics;

	@JsonProperty("mod:numberOfAgents")
	private List<Map<String, String>> numberOfAgents;

	@JsonProperty("mod:numberOfEnsorments")
	private List<Map<String, String>> numberOfEnsorments;

	@JsonProperty("mod:numberOfEvaluations")
	private List<Map<String, String>> numberOfEvaluations;

	@JsonProperty("mod:numberOfNotes")
	private List<Map<String, String>> numberOfNotes;

	@JsonProperty("mod:numberOfUsers")
	private List<Map<String, String>> numberOfUsers;

	@JsonProperty("mod:numberOfUsingProjects")
	private List<Map<String, String>> numberOfUsingProjects;

	@JsonProperty("mod:reliesOn")
	private List<Map<String, String>> reliesOn;

	@JsonProperty("mod:semanticArtefactRelation")
	private List<Map<String, String>> semanticArtefactRelation;

	@JsonProperty("mod:similar")
	private List<Map<String, String>> similar;

	@JsonProperty("mod:specializes")
	private List<Map<String, String>> specializes;

	@JsonProperty("mod:status")
	private List<Map<String, String>> status;

	@JsonProperty("mod:toDoList")
	private List<Map<String, String>> toDoList;

	@JsonProperty("mod:URI")
	private List<Map<String, String>> URI;

	@JsonProperty("mod:usedBy")
	private List<Map<String, String>> usedBy;

	@JsonProperty("mod:usedInProject")
	private List<Map<String, String>> usedInProject;

	@JsonProperty("owl:deprecated")
	private List<Map<String, String>> deprecated;

	@JsonProperty("owl:priorVersion")
	private List<Map<String, String>> priorVersion;

	@JsonProperty("owl:versionInfo")
	private List<Map<String, String>> versionInfo;

	@JsonProperty("owl:versionIRI")
	private List<Map<String, String>> versionIRI;

	@JsonProperty("prov:wasGeneratedBy")
	private List<Map<String, String>> wasGeneratedBy;

	@JsonProperty("prov:wasInvalidatedBy")
	private List<Map<String, String>> wasInvalidatedBy;

	@JsonProperty("rdfs:comment")
	private List<Map<String, String>> comment;

	@JsonProperty("schema:associatedMedia")
	private List<Map<String, String>> associatedMedia;

	@JsonProperty("schema:award")
	private List<Map<String, String>> award;

	@JsonProperty("schema:comment")
	private List<Map<String, String>> schemaComment;

	@JsonProperty("schema:funding")
	private List<Map<String, String>> funding;

	@JsonProperty("schema:includedInDataCatalog")
	private List<Map<String, String>> includedInDataCatalog;

	@JsonProperty("schema:translator")
	private List<Map<String, String>> translator;

	@JsonProperty("skos:hiddenLabel")
	private List<Map<String, String>> hiddenLabel;

	@JsonProperty("vann:changes")
	private List<Map<String, String>> changes;

	@JsonProperty("vann:example")
	private List<Map<String, String>> example;

	@JsonProperty("vann:preferredNamespacePrefix")
	private List<Map<String, String>> preferredNamespacePrefix;

	@JsonProperty("vann:preferredNamespaceUri")
	private List<Map<String, String>> preferredNamespaceUri;

	@JsonProperty("void:openSearchDescription")
	private List<Map<String, String>> openSearchDescription;

	@JsonProperty("void:classPartition")
	private List<Map<String, String>> classPartition;

	@JsonProperty("void:exampleResource")
	private List<Map<String, String>> exampleResource;

	@JsonProperty("void:rootResource")
	private List<Map<String, String>> rootResource;

	@JsonProperty("void:propertyPartition")
	private List<Map<String, String>> propertyPartition;

	@JsonProperty("void:uriLookupEndpoint")
	private List<Map<String, String>> uriLookupEndpoint;

	@JsonProperty("void:uriRegexPattern")
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
