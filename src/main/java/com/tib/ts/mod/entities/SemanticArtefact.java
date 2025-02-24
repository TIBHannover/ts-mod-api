package com.tib.ts.mod.entities;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

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
	private Map<String, Object> useGuidelines;

	@JsonProperty("cc:morePermissions")
	private Map<String, Object> morePermissions;

	@JsonProperty("dcat:downloadURL")
	private Map<String, Object> downloadURL;

	@JsonProperty("foaf:primaryTopic")
	private Map<String, Object> primaryTopic;

	@JsonProperty("dcterms:abstract")
	private Map<String, Object> abstracts;

	@JsonProperty("dcterms:accrualMethod")
	private Map<String, Object> accrualMethod;

	@JsonProperty("dcterms:accrualPeriodicity")
	private Map<String, Object> accrualPeriodicity;

	@JsonProperty("dcterms:accrualPolicy")
	private Map<String, Object> accrualPolicy;

	@JsonProperty("dcterms:alternative")
	private Map<String, Object> alternative;

	@JsonProperty("dcterms:audience")
	private Map<String, Object> audience;

	@JsonProperty("dcterms:bibliographicCitation")
	private Map<String, Object> bibliographicCitation;

	@JsonProperty("dcterms:contributor")
	private List<Map<String, Object>> contributor;

	@JsonProperty("dcterms:coverage")
	private Map<String, Object> coverage;

	@JsonProperty("dcterms:hasFormat")
	private Map<String, Object> hasFormat;

	@JsonProperty("dcterms:hasPart")
	private Map<String, Object> hasPart;

	@JsonProperty("dct:hasVersion")
	private Map<String, Object> hasVersion;

	@JsonProperty("dcterms:isFormatOf")
	private Map<String, Object> isFormatOf;

	@JsonProperty("dct:isPartOf")
	private Map<String, Object> isPartOf;

	@JsonProperty("dcterms:rightsHolder")
	private Map<String, Object> rightsHolder;

	@JsonProperty("dcterms:source")
	private Map<String, Object> source;

	@JsonProperty("doap:bug-database")
	private Map<String, Object> bugDatabase;

	@JsonProperty("doap:mailing-list")
	private Map<String, Object> mailingList;

	@JsonProperty("doap:repository")
	private Map<String, Object> repository;

	@JsonProperty("foaf:depiction")
	private Map<String, Object> depiction;

	@JsonProperty("foaf:fundedBy")
	private Map<String, Object> fundedBy;

	@JsonProperty("foaf:homepage")
	private Map<String, Object> homepage;

	@JsonProperty("foaf:logo")
	private Map<String, Object> logo;

	@JsonProperty("mod:acronym")
	private Map<String, Object> acronym;

	@JsonProperty("mod:analytics")
	private Map<String, Object> analytics;

	@JsonProperty("mod:comesFromTheSameDomain")
	private Map<String, Object> comesFromTheSameDomain;

	@JsonProperty("mod:competencyQuestion")
	private Map<String, Object> competencyQuestion;

	@JsonProperty("mod:curatedBy")
	private Map<String, Object> curatedBy;

	@JsonProperty("mod:createdWith")
	private Map<String, Object> createdWith;

	@JsonProperty("mod:designedForTask")
	private SemanticArtefactService designedForTask;

	@JsonProperty("mod:endorsedBy")
	private Map<String, Object> endorsedBy;

	@JsonProperty("mod:generalizes")
	private Map<String, Object> generalizes;

	@JsonProperty("mod:group")
	private Map<String, Object> group;

	@JsonProperty("mod:hasDisjunctionsWith")
	private Map<String, Object> hasDisjunctionsWith;

	@JsonProperty("mod:hasDisparateModelling")
	private Map<String, Object> hasDisparateModelling;

	@JsonProperty("mod:hasEquivalencesWith")
	private Map<String, Object> hasEquivalencesWith;

	@JsonProperty("mod:hasEvaluation")
	private Map<String, Object> hasEvaluation;

	@JsonProperty("mod:hasFormalityLevel")
	private Map<String, Object> hasFormalityLevel;

	@JsonProperty("mod:knownUsage")
	private Map<String, Object> knownUsage;

	@JsonProperty("mod:metrics")
	private Map<String, Object> metrics;

	@JsonProperty("mod:numberOfAgents")
	private Map<String, Object> numberOfAgents;

	@JsonProperty("mod:numberOfEnsorments")
	private Map<String, Object> numberOfEnsorments;

	@JsonProperty("mod:numberOfEvaluations")
	private Map<String, Object> numberOfEvaluations;

	@JsonProperty("mod:numberOfNotes")
	private Map<String, Object> numberOfNotes;

	@JsonProperty("mod:numberOfUsers")
	private Map<String, Object> numberOfUsers;

	@JsonProperty("mod:numberOfUsingProjects")
	private Map<String, Object> numberOfUsingProjects;

	@JsonProperty("mod:reliesOn")
	private Map<String, Object> reliesOn;

	@JsonProperty("mod:semanticArtefactRelation")
	private Map<String, Object> semanticArtefactRelation;

	@JsonProperty("mod:similar")
	private Map<String, Object> similar;

	@JsonProperty("mod:specializes")
	private Map<String, Object> specializes;

	@JsonProperty("mod:status")
	private Map<String, Object> status;

	@JsonProperty("mod:toDoList")
	private Map<String, Object> toDoList;

	@JsonProperty("mod:URI")
	private Map<String, Object> URI;

	@JsonProperty("mod:usedBy")
	private Map<String, Object> usedBy;

	@JsonProperty("mod:usedInProject")
	private Map<String, Object> usedInProject;

	@JsonProperty("owl:deprecated")
	private Map<String, Object> deprecated;

	@JsonProperty("owl:priorVersion")
	private Map<String, Object> priorVersion;

	@JsonProperty("owl:versionInfo")
	private Map<String, Object> versionInfo;

	@JsonProperty("owl:versionIRI")
	private Map<String, Object> versionIRI;

	@JsonProperty("prov:wasGeneratedBy")
	private Map<String, Object> wasGeneratedBy;

	@JsonProperty("prov:wasInvalidatedBy")
	private Map<String, Object> wasInvalidatedBy;

	@JsonProperty("rdfs:comment")
	private Map<String, Object> comment;

	@JsonProperty("schema:associatedMedia")
	private Map<String, Object> associatedMedia;

	@JsonProperty("schema:award")
	private Map<String, Object> award;

	@JsonProperty("schema:comment")
	private Map<String, Object> schemaComment;

	@JsonProperty("schema:funding")
	private Map<String, Object> funding;

	@JsonProperty("schema:includedInDataCatalog")
	private Map<String, Object> includedInDataCatalog;

	@JsonProperty("schema:translator")
	private Map<String, Object> translator;

	@JsonProperty("skos:hiddenLabel")
	private Map<String, Object> hiddenLabel;

	@JsonProperty("vann:changes")
	private Map<String, Object> changes;

	@JsonProperty("vann:example")
	private Map<String, Object> example;

	@JsonProperty("vann:preferredNamespacePrefix")
	private Map<String, Object> preferredNamespacePrefix;

	@JsonProperty("vann:preferredNamespaceUri")
	private Map<String, Object> preferredNamespaceUri;

	@JsonProperty("void:openSearchDescription")
	private Map<String, Object> openSearchDescription;

	@JsonProperty("void:classPartition")
	private Map<String, Object> classPartition;

	@JsonProperty("void:exampleResource")
	private Map<String, Object> exampleResource;

	@JsonProperty("void:rootResource")
	private Map<String, Object> rootResource;

	@JsonProperty("void:propertyPartition")
	private Map<String, Object> propertyPartition;

	@JsonProperty("void:uriLookupEndpoint")
	private Map<String, Object> uriLookupEndpoint;

	@JsonProperty("void:uriRegexPattern")
	private Map<String, Object> uriRegexPattern;

	@JsonGetter("@id")
    public String getIdAsString() {
        return semanticArtefactId != null ? semanticArtefactId.toString() : null;
    }
	
	@JsonGetter("@type")
    public String getTypeAsString() {
        return semanticArtefactType != null ? semanticArtefactType.toString() : "mod:semanticArtefact";
    }
}
