package com.tib.ts.mod.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tib.ts.mod.config.DynamicSerializer;

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
@JsonSerialize(using = DynamicSerializer.class)
@JsonRootName(value = "mod:SemanticArtefact")
public class SemanticArtefact extends Resource{
	
	@JsonProperty("@context")
	private Map<String, String> context;
	
	@JsonProperty("@id")
	private Object semanticArtefactId;
	
	@JsonProperty("@type")
	private Object semanticArtefactType;
	
	@JsonProperty("useGuidelines")
	private Object useGuidelines;

	@JsonProperty("morePermissions")
	private Object morePermissions;

	//@JsonProperty("downloadURL")
	//private Object downloadURL;

	@JsonProperty("primaryTopic")
	private Object primaryTopic;

	@JsonProperty("abstract")
	private Object abstracts;

	@JsonProperty("accrualMethod")
	private Object accrualMethod;

	@JsonProperty("accrualPeriodicity")
	private Object accrualPeriodicity;

	@JsonProperty("accrualPolicy")
	private Object accrualPolicy;

	@JsonProperty("alternative")
	private Object alternative;

	@JsonProperty("audience")
	private Object audience;

	@JsonProperty("bibliographicCitation")
	private Object bibliographicCitation;

	@JsonProperty("contributor")
	private Object contributor;

	@JsonProperty("coverage")
	private Object coverage;

	@JsonProperty("hasFormat")
	private Object hasFormat;

	@JsonProperty("hasPart")
	private Object hasPart;

	@JsonProperty("hasVersion")
	private Object hasVersion;

	@JsonProperty("isFormatOf")
	private Object isFormatOf;

	@JsonProperty("isPartOf")
	private Object isPartOf;

	@JsonProperty("rightsHolder")
	private Object rightsHolder;

	@JsonProperty("source")
	private Object source;

	@JsonProperty("bug-database")
	private Object bugDatabase;

	@JsonProperty("mailing-list")
	private Object mailingList;

	@JsonProperty("repository")
	private Object repository;

	@JsonProperty("depiction")
	private Object depiction;

	@JsonProperty("fundedBy")
	private Object fundedBy;

	@JsonProperty("homepage")
	private Object homepage;

	@JsonProperty("logo")
	private Object logo;

	@JsonProperty("acronym")
	private Object acronym;

	@JsonProperty("analytics")
	private Object analytics;

	@JsonProperty("comesFromTheSameDomain")
	private Object comesFromTheSameDomain;

	@JsonProperty("competencyQuestion")
	private Object competencyQuestion;

	//@JsonProperty("curatedBy")
	//private Object curatedBy;

	//@JsonProperty("createdWith")
	//private Object createdWith;

	@JsonProperty("designedForTask")
	private Object designedForTask;

	@JsonProperty("endorsedBy")
	private Object endorsedBy;

	@JsonProperty("generalizes")
	private Object generalizes;

	@JsonProperty("group")
	private Object group;

	@JsonProperty("hasDisjunctionsWith")
	private Object hasDisjunctionsWith;

	@JsonProperty("hasDisparateModelling")
	private Object hasDisparateModelling;

	@JsonProperty("hasEquivalencesWith")
	private Object hasEquivalencesWith;

	@JsonProperty("hasEvaluation")
	private Object hasEvaluation;

	//@JsonProperty("hasFormalityLevel")
	//private Object hasFormalityLevel;

	@JsonProperty("knownUsage")
	private Object knownUsage;

	@JsonProperty("metrics")
	private Object metrics;

	@JsonProperty("numberOfAgents")
	private Object numberOfAgents;

	@JsonProperty("numberOfEndorsements")
	private Object numberOfEndorsements;

	@JsonProperty("numberOfEvaluations")
	private Object numberOfEvaluations;

	@JsonProperty("numberOfNotes")
	private Object numberOfNotes;

	@JsonProperty("numberOfUsers")
	private Object numberOfUsers;

	@JsonProperty("numberOfUsingProjects")
	private Object numberOfUsingProjects;

	@JsonProperty("reliesOn")
	private Object reliesOn;

	@JsonProperty("semanticArtefactRelation")
	private Object semanticArtefactRelation;

	@JsonProperty("similar")
	private Object similar;

	@JsonProperty("specializes")
	private Object specializes;

	@JsonProperty("status")
	private Object status;

	@JsonProperty("toDoList")
	private Object toDoList;

	@JsonProperty("URI")
	private Object uri;

	@JsonProperty("usedBy")
	private Object usedBy;

	@JsonProperty("usedInProject")
	private Object usedInProject;
	
	@JsonProperty("backwardCompatibleWith")
	private Object backwardCompatibleWith;

	@JsonProperty("deprecated")
	private Object deprecated;
	
	@JsonProperty("incompatibleWith")
	private Object incompatibleWith;

	@JsonProperty("priorVersion")
	private Object priorVersion;

	@JsonProperty("versionInfo")
	private Object versionInfo;

	@JsonProperty("versionIRI")
	private Object versionIRI;
	
	@JsonProperty("curatedBy")
	private Object curatedBy;
	
	@JsonProperty("createdWith")
	private Object createdWith;

	@JsonProperty("wasGeneratedBy")
	private Object wasGeneratedBy;

	@JsonProperty("wasInvalidatedBy")
	private Object wasInvalidatedBy;

	@JsonProperty("comment")
	private Object comment;

	@JsonProperty("associatedMedia")
	private Object associatedMedia;

	@JsonProperty("award")
	private Object award;

	@JsonProperty("schemaComment")
	private Object schemaComment;

	@JsonProperty("funding")
	private Object funding;

	@JsonProperty("includedInDataCatalog")
	private Object includedInDataCatalog;

	@JsonProperty("translator")
	private Object translator;

	@JsonProperty("workTranslation")
	private Object workTranslation;

	@JsonProperty("hiddenLabel")
	private Object hiddenLabel;

	@JsonProperty("changes")
	private Object changes;

	@JsonProperty("example")
	private Object example;

	@JsonProperty("preferredNamespacePrefix")
	private Object preferredNamespacePrefix;

	@JsonProperty("preferredNamespaceUri")
	private Object preferredNamespaceUri;

	@JsonProperty("openSearchDescription")
	private Object openSearchDescription;

	@JsonProperty("classPartition")
	private Object classPartition;

	@JsonProperty("exampleResource")
	private Object exampleResource;

	@JsonProperty("rootResource")
	private Object rootResource;

	@JsonProperty("propertyPartition")
	private Object propertyPartition;

	@JsonProperty("uriLookupEndpoint")
	private Object uriLookupEndpoint;

	@JsonProperty("uriRegexPattern")
	private Object uriRegexPattern;

	@JsonGetter("@id")
    public String getIdAsString() {
        return semanticArtefactId != null ? semanticArtefactId.toString() : null;
    }
	
	@JsonGetter("@type")
    public String getTypeAsString() {
        return semanticArtefactType != null ? semanticArtefactType.toString() : "mod:semanticArtefact";
    }
}
