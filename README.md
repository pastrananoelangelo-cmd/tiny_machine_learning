# Tiny Machine Learning Framework (Java)

## Overview

This project is a machine learning framework built completely from scratch in Java.

The purpose of this project is not to compete with existing machine learning libraries, but to understand how they are built internally.

The project is divided into two phases:

* **Phase A — Data Layer**
* **Phase B — Learning Layer**

Every component is implemented manually without using machine learning libraries.

---

# Phase A — Data Layer

## Goal

Understand everything that happens before a machine learning model begins learning.

Implemented:

* CSV Loading
* Missing Value Handling
* Duplicate Removal
* Feature Encoding
* Feature Normalization
* Train/Test Split
* Feature/Target Split
* Data Pipeline Architecture

Final outputs:

* X_train
* y_train
* X_test
* y_test

---

# Phase B — Learning Layer

## Goal

Understand what machine learning actually means.

Instead of calling an existing ML library, this phase builds the learning process manually.

Implemented:

* Linear Regression
* Model Interface
* Weight Initialization
* Prediction
* Error Computation
* Squared Loss
* Gradient Descent
* Epoch-based Learning
* Training Evaluation
* Testing Evaluation

The objective of this phase is to understand how a machine learning model gradually improves its predictions by adjusting its internal weights.

---

# Overall Project Pipeline

```
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

LinearRegression

↓

Initialize Weights

↓

Prediction

↓

Error

↓

Weight Update (Gradient Descent)

↓

Repeat for Multiple Epochs

↓

Evaluate

↓

Training Loss
Testing Loss
```

---

# Project Structure

```
src/

Main.java

pipeline/
    DataPipeline.java

dataset/
    CSVLoader.java

clean/
    DataCleaner.java
    MissingValueCleaner.java
    DuplicateCleaner.java

transform/
    Transform.java
    Encoder.java
    Normalizer.java
    TrainTestSplit.java
    FeatureTargetSplit.java
    FeatureTargetDataset.java

model/
    Model.java
    LinearRegression.java

core/
    Dataset.java
```

---

# Phase A Components

## Dataset

Stores tabular data using:

```
List<Map<String, String>>
```

Responsibilities:

* Store rows
* Preview datasets
* Provide transformed data

---

## CSVLoader

Loads CSV files into Dataset.

---

## DataCleaner

Coordinates:

* MissingValueCleaner
* DuplicateCleaner

---

## MissingValueCleaner

Features:

* Automatic numeric/categorical detection
* Numeric value replacement
* Categorical value replacement
* Deep-copy protection

---

## DuplicateCleaner

Features:

* Ignore student_id
* Preserve first occurrence
* Preserve dataset order

---

## Encoder

Converts categorical variables into numerical values.

Strategy:

* Order of appearance

Example:

```
CS  -> 0
IT  -> 1
ENG -> 2
BUS -> 3
```

---

## Normalizer

Uses Min-Max Scaling:

```
(value - min)
----------------
(max - min)
```

Scales continuous numerical features between 0 and 1.

---

## TrainTestSplit

Responsibilities:

* Shuffle rows
* 80/20 split
* Preserve target distribution

---

## FeatureTargetSplit

Produces:

* X_train
* y_train
* X_test
* y_test

---

## Transform

Coordinates every preprocessing step.

Pipeline:

Encoder

↓

Normalizer

↓

TrainTestSplit

↓

FeatureTargetSplit

---

## DataPipeline

Single entry point for preprocessing.

Flow:

Load

↓

Clean

↓

Transform

↓

Training-ready Dataset

---

# Phase B Components

## Model Interface

Defines the common behavior of every machine learning model.

Current operations:

* fit()
* predict()
* evaluate()

This allows future models (such as Logistic Regression) to follow the same API.

---

## LinearRegression

Built completely from scratch.

Responsibilities:

* Initialize feature weights
* Learn from training data
* Predict continuous values
* Update weights using Gradient Descent
* Evaluate model performance

---

## Weight Initialization

The model initially assumes every feature is equally important.

```
All weights = 1.0
```

During training, these weights gradually change to represent the learned importance of every feature.

---

## Prediction

Prediction is computed as:

```
prediction =
bias +
Σ(feature × weight)
```

Every prediction represents the model's current belief.

---

## Error

Measures how wrong the prediction is.

```
error = prediction - actual
```

The error guides how the weights should change.

---

## Loss

Uses Squared Error:

```
loss = error²
```

The model uses average loss to measure overall performance.

---

## Gradient Descent

Weights are updated using:

```
weight =
weight
-
learningRate
×
error
×
featureValue
```

The update changes the model's internal beliefs so future predictions become more accurate.

---

## Epochs

The model repeatedly learns from the entire training dataset.

Each epoch:

Training Data

↓

Prediction

↓

Error

↓

Loss

↓

Weight Update

↓

Repeat

As training progresses, the average loss decreases.

---

## Evaluation

After training, the model evaluates both:

* Training Loss
* Testing Loss

Testing never updates the weights.

Its purpose is to measure how well the learned patterns generalize to unseen data.

---

# Concepts Learned

Machine Learning Concepts

* Features
* Targets
* Weights
* Bias
* Prediction
* Error
* Squared Loss
* Gradient Descent
* Learning Rate
* Epochs
* Generalization
* Training vs Testing
* Overfitting

Software Engineering Concepts

* Interfaces
* Encapsulation
* Separation of Concerns
* Composition
* Pipeline Design
* API Design
* Wrapper Objects
* Package Organization

Data Structures

* ArrayList
* HashMap
* LinkedHashMap
* HashSet
* LinkedHashSet

---

# Current Result

The framework can now:

* Load raw CSV datasets
* Clean missing values
* Remove duplicates
* Encode categorical features
* Normalize continuous features
* Split datasets
* Train a Linear Regression model
* Learn through Gradient Descent
* Reduce training loss over multiple epochs
* Evaluate both training and testing performance

Every component has been implemented manually in Java to understand the internal mechanics of machine learning.

---

# Next Phase

## Phase C — Expanding the Learning Layer

Planned additions:

* Logistic Regression
* Probability Prediction
* Sigmoid Function
* Classification Decision Boundary
* Additional Evaluation Metrics

Goal:

Understand how classification models extend the same learning principles established in Linear Regression.
