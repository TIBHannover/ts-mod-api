# Metadata for Ontology Description and Publication Ontology (MOD)

MOD API is available at <b>[https://service.tib.eu/terminology/mod/swagger-ui/index.html](https://service.tib.eu/terminology/mod/swagger-ui/index.html)</b>. Please report any issues to the
tracker in this repository.

---

# Deploying MOD

If you want to try MOD APIs, follow below requirement & script:

## Prerequisite
1. MOD depends on TS OLS4 API. Make sure TS OLS4 API is available at [https://api.terminology.tib.eu](https://api.terminology.tib.eu). 
2. Java 17. Later versions of Java are probably fine.

### Acquire source and build
Clone repo:

    git clone https://github.com/TIBHannover/ts-mod-api.git

Build backend:

    mvn clean package

Run:
 	
	cd {CLONED_REPO}
	docker compose up
 