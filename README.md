# ML Data Preprocessing Playground — Phase A (Data Layer)

## Overview

This project is a machine learning preprocessing playground built from scratch in Java.

The goal of Phase A is to understand and implement everything that happens before a machine learning model is trained.

No machine learning algorithm is used yet.

Instead, this phase focuses on transforming raw CSV data into training-ready and testing-ready datasets.

Final outputs:

* X_train
* y_train
* X_test
* y_test

---

## Goals

Learn and implement:

* Loading structured data
* Data cleaning
* Missing value handling
* Duplicate removal
* Feature encoding
* Feature normalization
* Train/Test splitting
* Feature/Target separation
* Pipeline architecture

---

# Project Pipeline

dataset.csv

↓

CSVLoader

↓

DataCleaner

↓

Transform

├── Encoder
├── Normalizer
├── TrainTestSplit
└── FeatureTargetSplit

↓

FeatureTargetDataset

↓

X_train / y_train

X_test / y_test

---

# Project Structure

src/

Main.java

pipeline/

* DataPipeline.java

dataset/

* CSVLoader.java

clean/

* DataCleaner.java
* MissingValueCleaner.java
* DuplicateCleaner.java

transform/

* Transform.java
* Encoder.java
* Normalizer.java
* TrainTestSplit.java
* FeatureTargetSplit.java
* FeatureTargetDataset.java

core/

* Dataset.java

---

# Components

## Dataset

Purpose:
Store tabular data.

Structure:

List<Map<String, String>>

Responsibilities:

* Store rows
* Add rows
* Preview dataset
* Access transformed data

---

## CSVLoader

Purpose:
Load CSV files into Dataset.

Input:

Raw CSV

Output:

Dataset

---

## DataCleaner

Purpose:
Coordinate cleaning steps.

Pipeline:

MissingValueCleaner

↓

DuplicateCleaner

Output:

Clean Dataset

---

## MissingValueCleaner

Purpose:
Handle missing values.

Implemented:

Numeric columns:

* Replace null using calculated values

Categorical columns:

* Replace null using detected category

Features:

* Automatic type detection
* Deep copy protection

Result:

No null values remain.

---

## DuplicateCleaner

Purpose:
Remove duplicate rows.

Rules:

* Ignore student_id
* Preserve first occurrence
* Preserve row order

Implementation:

* LinkedHashSet
* Composite row comparison

Result:

Unique rows only.

---

## Encoder

Purpose:
Convert categorical features into numeric representations.

Rules:

* Preserve student_id
* Preserve target column (passed)
* Encode only predictors

Encoding strategy:

Order of appearance

Example:

department

CS → 0
IT → 1
ENG → 2
BUS → 3

internet_access

yes → 0
no → 1

Output:

Encoded Dataset

---

## Normalizer

Purpose:
Normalize numeric features.

Method:

Min-Max Scaling

Formula:

normalized =
(value − min)
/
(max − min)

Rules:

* Skip student_id
* Skip target column
* Skip encoded categorical columns

Output:

Values scaled between 0 and 1

---

## TrainTestSplit

Purpose:
Split data into training and testing datasets.

Strategy:

* Shuffle rows
* Preserve target distribution
* Floor split size
* 80 / 20 split

Output:

train
test

Implementation:

SplitDataset wrapper

---

## FeatureTargetSplit

Purpose:
Separate predictors and labels.

Rules:

X:

* Remove student_id
* Remove passed

y:

* Extract passed
* Encode:

yes → "1"
no → "0"

Output:

X_train
y_train

X_test
y_test

---

## FeatureTargetDataset

Purpose:

Store final preprocessing outputs.

Contains:

Dataset xTrain

List<String> yTrain

Dataset xTest

List<String> yTest

---

## Transform

Purpose:

Group all transformation steps.

Pipeline:

Encoder

↓

Normalizer

↓

TrainTestSplit

↓

FeatureTargetSplit

Output:

FeatureTargetDataset

---

## DataPipeline

Purpose:

Single entry point for the application.

Flow:

Load

↓

Clean

↓

Transform

↓

Return training-ready data

---

## Main

Purpose:

Application entry point.

Example:

Create DataPipeline

↓

Run pipeline

↓

Receive FeatureTargetDataset

---

# Concepts Practiced

Data Structures:

* ArrayList
* HashMap
* LinkedHashMap
* HashSet
* LinkedHashSet

Programming Concepts:

* Deep Copy
* Mutable vs Immutable Operations
* Composition
* Separation of Concerns
* Encapsulation
* Pipeline Design
* Data Transformation
* Package Organization
* Wrapper Objects

---

# Phase A Result

Input:

Raw CSV Dataset

Output:

Training-ready data

* X_train
* y_train
* X_test
* y_test

No machine learning yet.

Only preparing data.

---

# Next Phase

Phase B — Model Layer

Goal:

Teach the machine.

Planned direction:

model.train(
xTrain,
yTrain,
xTest,
yTest
)
