package com.tib.ts.mod.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 *@author Deepan Anbalagan
 *@email deepan.anbalagan@tib.eu
 *TIB-Leibniz Information Center for Science and Technology
*/

@Getter
@Setter
public class SemanticArtefact extends Resource {
	
	@JsonProperty("@id")
	private String semanticArtefactId;
	
	@JsonProperty("@type")
	private String semanticArtefactType;
	
	@JsonProperty("cc:useGuidelines")
	private Map<String, String> useGuidelines;

	@JsonProperty("cc:morePermissions")
	private Map<String, String> morePermissions;

	@JsonProperty("dcat:downloadURL")
	private Map<String, String> downloadURL;

	@JsonProperty("foaf:primaryTopic")
	private Map<String, String> primaryTopic;

	@JsonProperty("dcterms:abstract")
	private Map<String, String> abstract_;

	@JsonProperty("dcterms:accrualMethod")
	private Map<String, String> accrualMethod;

	@JsonProperty("dcterms:accrualPeriodicity")
	private Map<String, String> accrualPeriodicity;

	@JsonProperty("dcterms:accrualPolicy")
	private Map<String, String> accrualPolicy;

	@JsonProperty("dcterms:alternative")
	private Map<String, String> alternative;

	@JsonProperty("dcterms:audience")
	private Map<String, String> audience;

	@JsonProperty("dcterms:bibliographicCitation")
	private Map<String, String> bibliographicCitation;

	@JsonProperty("dcterms:contributor")
	private Map<String, String> contributor;

	@JsonProperty("dcterms:coverage")
	private Map<String, String> coverage;

	@JsonProperty("dcterms:hasFormat")
	private Map<String, String> hasFormat;

	@JsonProperty("dcterms:hasPart")
	private Map<String, String> hasPart;

	@JsonProperty("dct:hasVersion")
	private Map<String, String> hasVersion;

	@JsonProperty("dcterms:isFormatOf")
	private Map<String, String> isFormatOf;

	@JsonProperty("dct:isPartOf")
	private Map<String, String> isPartOf;

	@JsonProperty("dcterms:rightsHolder")
	private Map<String, String> rightsHolder;

	@JsonProperty("dcterms:source")
	private Map<String, String> source;

	@JsonProperty("doap:bug-database")
	private Map<String, String> bugDatabase;

	@JsonProperty("doap:mailing-list")
	private Map<String, String> mailingList;

	@JsonProperty("doap:repository")
	private Map<String, String> repository;

	@JsonProperty("foaf:depiction")
	private Map<String, String> depiction;

	@JsonProperty("foaf:fundedBy")
	private Map<String, String> fundedBy;

	@JsonProperty("foaf:homepage")
	private Map<String, String> homepage;

	@JsonProperty("foaf:logo")
	private Map<String, String> logo;

	@JsonProperty("mod:acronym")
	private Map<String, String> acronym;

	@JsonProperty("mod:analytics")
	private Map<String, String> analytics;

	@JsonProperty("mod:comesFromTheSameDomain")
	private Map<String, String> comesFromTheSameDomain;

	@JsonProperty("mod:competencyQuestion")
	private Map<String, String> competencyQuestion;

	@JsonProperty("mod:curatedBy")
	private Map<String, String> curatedBy;

	@JsonProperty("mod:createdWith")
	private Map<String, String> createdWith;

	@JsonProperty("mod:designedForTask")
	private SemanticArtefactService designedForTask;

	@JsonProperty("mod:endorsedBy")
	private Map<String, String> endorsedBy;

	@JsonProperty("mod:generalizes")
	private Map<String, String> generalizes;

	@JsonProperty("mod:group")
	private Map<String, String> group;

	@JsonProperty("mod:hasDisjunctionsWith")
	private Map<String, String> hasDisjunctionsWith;

	@JsonProperty("mod:hasDisparateModelling")
	private Map<String, String> hasDisparateModelling;

	@JsonProperty("mod:hasEquivalencesWith")
	private Map<String, String> hasEquivalencesWith;

	@JsonProperty("mod:hasEvaluation")
	private Map<String, String> hasEvaluation;

	@JsonProperty("mod:hasFormalityLevel")
	private Map<String, String> hasFormalityLevel;

	@JsonProperty("mod:knownUsage")
	private Map<String, String> knownUsage;

	@JsonProperty("mod:metrics")
	private Map<String, String> metrics;

	@JsonProperty("mod:numberOfAgents")
	private Map<String, String> numberOfAgents;

	@JsonProperty("mod:numberOfEnsorments")
	private Map<String, String> numberOfEnsorments;

	@JsonProperty("mod:numberOfEvaluations")
	private Map<String, String> numberOfEvaluations;

	@JsonProperty("mod:numberOfNotes")
	private Map<String, String> numberOfNotes;

	@JsonProperty("mod:numberOfUsers")
	private Map<String, String> numberOfUsers;

	@JsonProperty("mod:numberOfUsingProjects")
	private Map<String, String> numberOfUsingProjects;

	@JsonProperty("mod:reliesOn")
	private Map<String, String> reliesOn;

	@JsonProperty("mod:semanticArtefactRelation")
	private Map<String, String> semanticArtefactRelation;

	@JsonProperty("mod:similar")
	private Map<String, String> similar;

	@JsonProperty("mod:specializes")
	private Map<String, String> specializes;

	@JsonProperty("mod:status")
	private Map<String, String> status;

	@JsonProperty("mod:toDoList")
	private Map<String, String> toDoList;

	@JsonProperty("mod:URI")
	private Map<String, String> URI;

	@JsonProperty("mod:usedBy")
	private Map<String, String> usedBy;

	@JsonProperty("mod:usedInProject")
	private Map<String, String> usedInProject;

	@JsonProperty("owl:deprecated")
	private Map<String, String> deprecated;

	@JsonProperty("owl:priorVersion")
	private Map<String, String> priorVersion;

	@JsonProperty("owl:versionInfo")
	private Map<String, String> versionInfo;

	@JsonProperty("owl:versionIRI")
	private Map<String, String> versionIRI;

	@JsonProperty("prov:wasGeneratedBy")
	private Map<String, String> wasGeneratedBy;

	@JsonProperty("prov:wasInvalidatedBy")
	private Map<String, String> wasInvalidatedBy;

	@JsonProperty("rdfs:comment")
	private Map<String, String> comment;

	@JsonProperty("schema:associatedMedia")
	private Map<String, String> associatedMedia;

	@JsonProperty("schema:award")
	private Map<String, String> award;

	@JsonProperty("schema:comment")
	private Map<String, String> schemaComment;

	@JsonProperty("schema:funding")
	private Map<String, String> funding;

	@JsonProperty("schema:includedInDataCatalog")
	private Map<String, String> includedInDataCatalog;

	@JsonProperty("schema:translator")
	private Map<String, String> translator;

	@JsonProperty("skos:hiddenLabel")
	private Map<String, String> hiddenLabel;

	@JsonProperty("vann:changes")
	private Map<String, String> changes;

	@JsonProperty("vann:example")
	private Map<String, String> example;

	@JsonProperty("vann:preferredNamespacePrefix")
	private Map<String, String> preferredNamespacePrefix;

	@JsonProperty("vann:preferredNamespaceUri")
	private Map<String, String> preferredNamespaceUri;

	@JsonProperty("void:openSearchDescription")
	private Map<String, String> openSearchDescription;

	@JsonProperty("void:classPartition")
	private Map<String, String> classPartition;

	@JsonProperty("void:exampleResource")
	private Map<String, String> exampleResource;

	@JsonProperty("void:rootResource")
	private Map<String, String> rootResource;

	@JsonProperty("void:propertyPartition")
	private Map<String, String> propertyPartition;

	@JsonProperty("void:uriLookupEndpoint")
	private Map<String, String> uriLookupEndpoint;

	@JsonProperty("void:uriRegexPattern")
	private Map<String, String> uriRegexPattern;


}
