
package io.sudhir.switchboard;

 final class AutoValue_ReducingRandomSupply extends ReducingRandomSupply {

  private final String type;

  AutoValue_ReducingRandomSupply(
      String type) {
    if (type == null) {
      throw new NullPointerException("Null type");
    }
    this.type = type;
  }

  @Override
  String type() {
    return type;
  }

  @Override
  public String toString() {
    return "ReducingRandomSupply{"
        + "type=" + type
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof ReducingRandomSupply) {
      ReducingRandomSupply that = (ReducingRandomSupply) o;
      return (this.type.equals(that.type()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h = 1;
    h *= 1000003;
    h ^= this.type.hashCode();
    return h;
  }

}
