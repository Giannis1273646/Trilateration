# 📍 Trilateration

A Java application that reads nodes from a JSON file, computes their combinations, and applies **trilateration** to estimate locations based on distance data.

This project is useful for simulating localization techniques, such as those used in GPS, sensor networks, or IoT environments.

---

## 🧠 How It Works

1. **Input Data**  
   - Nodes (with coordinates and distances) are loaded from a JSON file.
   - The number of nodes and their data are parsed and displayed.

2. **Combinations**  
   - All possible combinations of nodes (default to 3 per group) are generated.
   - Combination length is adjustable for experimentation.

3. **Trilateration**  
   - For each valid combination, trilateration is executed to estimate a new point.
   - The output includes the calculated coordinates for each combination.

4. **Post-Processing**  
   - Outliers in the resulting points are detected and removed.
   - A clean set of trilaterated points is then visualized.

5. **Visualization**  
   - A 2D graph displays all valid trilaterated points after outlier filtering.

---

## 🧪 Example Flow

- Load JSON → Generate combinations → Apply trilateration → Remove outliers → Visualize

---

## 📊 Features

- ✅ Reads JSON node data
- ✅ Adjustable combination length
- ✅ Trilateration on all node groups
- ✅ Outlier filtering
- ✅ Graph visualization of results

---

Perfect — this gives a complete picture of your application and its functionality! Based on the graph, the output, and the JSON input format, here’s an **updated README section** that includes your visual and textual results:

---

## 📸 Application Output

### ✅ Input Data (JSON Format)

Your input should be a list of nodes in the following format:

```json
[
  { "id": "node1", "x": 1.0, "y": 3.0, "distance": 4.0 },
  { "id": "node2", "x": 5.0, "y": 6.0, "distance": 3.5 },
  { "id": "node3", "x": 10.0, "y": 4.0, "distance": 5.25 },
  { "id": "node4", "x": 9.0, "y": 1.0, "distance": 4.3 },
  { "id": "node5", "x": 3.0, "y": -2.0, "distance": 4.92 },
  { "id": "node6", "x": 8.0, "y": -2.0, "distance": 5.45 },
  { "id": "node7", "x": 8.0, "y": 8.0, "distance": 6.62 }
]
```

---

### 🧮 Combinations & Trilateration Results

- A total of **35 combinations** of 3 nodes were generated.
- The trilateration algorithm ran for each combination and estimated unknown point coordinates.
- Outliers were detected and removed based on distance deviation or invalid values (`Infinity`).

#### 🔍 Example Output:
```text
[node1, node2, node3]
Coordinates: (4.96875, 2.5)

[node1, node2, node7]
Coordinates: (-1.6116, 11.2738) ← Outlier

[node4, node6, node7]
Coordinates: (5.724535, 2.293905) ← Outlier

[node5, node6, node7]
Coordinates: (4.95039, Infinity) ← Invalid
```

---

### 📈 Graphical Output

This scatter plot visualizes the results:

- 🔵 **Blue Circles**: All valid estimated points
- 🔺 **Red Diamond**: Final average/desired point
- ⬛ **Black Squares**: Original nodes

![Screenshot 2025-04-06 192424](https://github.com/user-attachments/assets/f259e3bc-3135-43da-9550-b8fedfae0342)

---

## 🛠 Requirements

- Java 17+
- JSON parsing library (e.g., Jackson or Gson)
- JavaFX (optional, if UI/graph is built with it)

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/trilateration-app.git
   cd trilateration-app
   ```

2. Make sure all required libraries are set up in your project (e.g., JavaFX, JSON parser).

3. Run the `Main` class or your equivalent entry point.
