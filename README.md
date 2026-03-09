# 🌱 Spring IOC — TP2 : Injection de Dépendance

> TP2 d'initiation à l'**Inversion de Contrôle (IoC)** et l'**Injection de Dépendances (DI)** avec Spring Framework via les annotations.

---

##  Objectif

Créer une application Java avec Spring mettant en œuvre :
- L'**inversion de contrôle (IoC)**
- L'**injection de dépendances (DI)** via annotations
- Une architecture en couches : `dao` → `metier` → `presentation`

---

##  Structure du Projet

<img width="501" height="699" alt="image" src="https://github.com/user-attachments/assets/a8fba0a2-123f-4e5f-806d-01eae6289bdc" />

---


##  Lancer le Projet

### Prérequis
- Java 17+ (Java 25 recommandé avec Spring 7.x)
- Maven 3.x
- IntelliJ IDEA 

---

### Résultat attendu

```
Résultat = 250.0
```

> `DaoImpl2.getValue()` retourne `150.0` + 100 = **250.0** (via `MetierImpl2`)

---

## 📦 Configuration Maven (`pom.xml`)

```xml
<properties>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>7.0.2</version>
    </dependency>
</dependencies>
```

> ⚠️ Spring 7.x est requis pour Java 25. Spring 6.x supporte Java 17/21 uniquement.
---

## 🔑 Annotations Spring

| Annotation | Rôle |
|---|---|
| `@Component("nom")` | Enregistre la classe comme bean Spring |
| `@Autowired` | Demande à Spring d'injecter un bean automatiquement |
| `@Qualifier("nom")` | Précise quel bean injecter en cas d'ambiguïté |
| `@Configuration` | Déclare une classe de configuration Spring |
| `@ComponentScan` | Indique les packages à scanner pour trouver les beans |

---

## 💉 Types d'Injection supportés

### Par champ *(utilisé dans ce TP)*
```java
@Autowired
@Qualifier("dao2")
private IDao dao;
```

### Par setter *(alternative)*
```java
@Autowired
@Qualifier("dao2")
public void setDao(IDao dao) {
    this.dao = dao;
}
```

### Par constructeur *(recommandé en production)*
```java
@Autowired
public MetierImpl2(@Qualifier("dao2") IDao dao) {
    this.dao = dao;
}
```

---

## ⚠️ Règles importantes

- `@Qualifier` est **obligatoire** si 2+ beans du même type existent
- `@Qualifier` seul sans `@Autowired` ne fonctionne **pas**
- `getBean(IMetier.class)` échoue si 2 beans `IMetier` existent → utiliser `getBean("metier2")`
- Spring 7.x est nécessaire pour **Java 25** (class file version 69)

---

##  Flux d'Exécution

```
main() démarre
      ↓
Spring démarre via AnnotationConfigApplicationContext
      ↓
@ComponentScan scanne : dao/ metier/ presentation/
      ↓
Beans créés : DaoImpl, DaoImpl2, DaoImpl3, MetierImpl, MetierImpl2
      ↓
@Qualifier("dao2") → DaoImpl2 injecté dans MetierImpl2
      ↓
getBean("metier2") → retourne MetierImpl2 prêt
      ↓
metier.calcul() → 150.0 + 100 = 250.0 

```
## Resultat 

<img width="972" height="291" alt="image" src="https://github.com/user-attachments/assets/5501274b-e995-4dd1-aadb-a0f1516af687" />

---

## 🧱 Principes appliqués

| Principe | Description | Application |
|---|---|---|
| **IoC** | Inversion de Contrôle | Spring crée et gère les objets à ta place |
| **DI** | Injection de Dépendance | Spring injecte `IDao` dans `MetierImpl2` automatiquement |
| **SRP** | Single Responsibility | Chaque classe a un seul rôle |
| **OCP** | Open/Closed | Ajouter `DaoImpl3` sans modifier `MetierImpl` |
| **DIP** | Dependency Inversion | `MetierImpl2` dépend de `IDao` (interface), pas d'une classe concrète | 
